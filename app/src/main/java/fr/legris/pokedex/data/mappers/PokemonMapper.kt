package fr.legris.pokedex.data.mappers

import fr.legris.pokedex.data.api.model.Pokemon
import fr.legris.pokedex.data.bdd.model.PokemonEntity

class PokemonMapper {
    companion object{

        fun mapPokemonListToPokemonEntityList(pokemonList: List<Pokemon>) : List<PokemonEntity> =
            pokemonList.map { pokemon ->
                mapPokemonToPokemonEntity(pokemon)
            }

        private fun mapPokemonToPokemonEntity(pokemon: Pokemon) : PokemonEntity {
            val id = pokemon.url.split("/")[pokemon.url.split("/").size - 2].toInt()
            return PokemonEntity(
                id,
                pokemon.name,
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png"
            )
        }
    }
}