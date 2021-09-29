package fr.legris.pokedex.data.repository

import fr.legris.pokedex.data.api.PokemonService
import fr.legris.pokedex.data.api.model.PokemonList
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
        private val pokemonService: PokemonService
) : PokemonRepository {

    override suspend fun getPokeList(offset: Int, limit: Int): PokemonList =
            pokemonService.listPokemon(offset, limit).apply {
                
            }

}