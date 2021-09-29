package fr.legris.pokedex.data.repository

import fr.legris.pokedex.data.api.model.PokemonList

interface PokemonRepository {

    suspend fun getPokeList(offset: Int, limit: Int): PokemonList
}