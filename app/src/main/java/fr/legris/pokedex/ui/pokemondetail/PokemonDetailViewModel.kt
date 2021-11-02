package fr.legris.pokedex.ui.pokemondetail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.legris.pokedex.data.repository.PokemonRepository
import fr.legris.pokedex.ui.model.Pokemon
import fr.legris.pokedex.ui.model.PokemonFromList
import fr.legris.pokedex.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private val _pokemonId = MutableLiveData<Int>()

    private var _pokemon = _pokemonId.switchMap { pokemonId ->
        pokemonRepository.getPokemonById(pokemonId)
    }

    val pokemon: LiveData<Resource<Pokemon>>
        get() {
            return _pokemon
        }

    fun getPokemonById(pokemonId: Int) {
        _pokemonId.value = pokemonId
    }
}