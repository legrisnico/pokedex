package fr.legris.pokedex.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.legris.pokedex.data.repository.PokemonRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    pokemonRepository: PokemonRepository
) : ViewModel() {

    @ExperimentalPagingApi
    val pokemonFlow = pokemonRepository.getPokemonList().cachedIn(viewModelScope)

}