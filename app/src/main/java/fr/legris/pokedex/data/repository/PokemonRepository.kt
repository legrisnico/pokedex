package fr.legris.pokedex.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import fr.legris.pokedex.ui.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    @ExperimentalPagingApi
    fun getPokemonList(): Flow<PagingData<Pokemon>>
}