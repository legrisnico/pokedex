package fr.legris.pokedex.ui.pokemondetail

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.legris.pokedex.data.repository.PokemonRepository
import fr.legris.pokedex.ui.model.Pokemon
import fr.legris.pokedex.utils.Constants
import fr.legris.pokedex.utils.Resource
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private var _pokemon =
        savedStateHandle.getLiveData<Int>(Constants.ARG_POKEMON_ID).switchMap { pokemonId ->
            pokemonRepository.getPokemonById(pokemonId)
        }

    val pokemon: LiveData<Resource<Pokemon>>
        get() {
            return _pokemon
        }
}
