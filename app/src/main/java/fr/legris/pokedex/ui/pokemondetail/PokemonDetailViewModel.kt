package fr.legris.pokedex.ui.pokemondetail

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.legris.pokedex.data.repository.PokemonRepository
import fr.legris.pokedex.ui.model.Pokemon
import fr.legris.pokedex.ui.model.PokemonFromList
import fr.legris.pokedex.utils.Resource
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private val _id = MutableLiveData<Int>()

    private val _pokemon = _id.switchMap { id ->
        pokemonRepository.getPokemonById(id)
    }
    val pokemon: LiveData<Resource<Pokemon>> = _pokemon

    fun getPokemonById(pokemonId: Int) {
        _id.postValue(pokemonId)
    }
}