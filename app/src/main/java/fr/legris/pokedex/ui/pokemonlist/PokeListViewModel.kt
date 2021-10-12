package fr.legris.pokedex.ui.pokemonlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.legris.pokedex.data.api.model.PokemonListDTO
import fr.legris.pokedex.data.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class PokeListViewModel @Inject constructor(
        private val pokemonRepository: PokemonRepository
) : ViewModel()