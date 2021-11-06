package fr.legris.pokedex.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import fr.legris.pokedex.ui.model.Pokemon
import fr.legris.pokedex.ui.model.PokemonFromList
import fr.legris.pokedex.utils.Resource
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    @ExperimentalPagingApi
    fun getPokemonList(): Flow<PagingData<PokemonFromList>>

    fun getPokemonById(id: Int): LiveData<Resource<Pokemon>>
}
