package fr.legris.pokedex.data.repository

import androidx.paging.*
import fr.legris.pokedex.BuildConfig
import fr.legris.pokedex.data.api.PokemonService
import fr.legris.pokedex.data.bdd.dao.PokemonDao
import fr.legris.pokedex.data.bdd.model.PokemonEntity
import fr.legris.pokedex.data.mappers.PokemonMapper
import fr.legris.pokedex.data.repository.paging.PokemonListRemoteMediator
import fr.legris.pokedex.ui.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonDao : PokemonDao,
    private val pokemonService: PokemonService
) : PokemonRepository {

    @ExperimentalPagingApi
    override fun getPokemonList() : Flow<PagingData<Pokemon>> = Pager(
        config = PagingConfig(pageSize = BuildConfig.POKEMON_PAGE_SIZE),
        remoteMediator = PokemonListRemoteMediator(pokemonDao, pokemonService)
    ) {
        pokemonDao.pagingSource()
    }.flow.map { pagingData ->
        pagingData.map { pokemonEntity ->
            PokemonMapper().mapFromDbEntityToModelUi(pokemonEntity)
        }
    }

}