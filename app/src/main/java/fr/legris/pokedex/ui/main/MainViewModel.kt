package fr.legris.pokedex.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.legris.pokedex.BuildConfig
import fr.legris.pokedex.data.api.PokemonService
import fr.legris.pokedex.data.bdd.dao.PokemonDao
import fr.legris.pokedex.data.repository.PokemonListRemoteMediator
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val pokemonDao : PokemonDao,
    pokemonService: PokemonService
) : ViewModel() {

    @ExperimentalPagingApi
    val pokemonFlow = Pager(
        config = PagingConfig(pageSize = BuildConfig.POKEMON_PAGE_SIZE),
        remoteMediator = PokemonListRemoteMediator(pokemonDao, pokemonService)
    ) {
        pokemonDao.pagingSource()
    }.flow.cachedIn(viewModelScope)

}