package fr.legris.pokedex.ui.pokemonlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.legris.pokedex.data.api.model.PokemonList
import fr.legris.pokedex.data.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class PokeListViewModel @Inject constructor(
        private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private var _pokemonList: LiveData<PokemonList> = liveData(Dispatchers.IO) {
        emit(pokemonRepository.getPokeList(0,20))
    }
    val pokemonList = _pokemonList

}