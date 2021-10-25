package fr.legris.pokedex.ui.pokemonlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.legris.pokedex.data.api.model.PokemonListDTO
import fr.legris.pokedex.data.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
        pokemonRepository: PokemonRepository
) : ViewModel() {

        @ExperimentalPagingApi
        val pokemonFlow = pokemonRepository.getPokemonList().cachedIn(viewModelScope)
}