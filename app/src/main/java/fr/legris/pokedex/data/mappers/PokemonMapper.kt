package fr.legris.pokedex.data.mappers

import fr.legris.pokedex.data.api.model.Pokemon
import fr.legris.pokedex.data.bdd.model.PokemonEntity

class PokemonMapper: DbEntityMapper<PokemonEntity, Pokemon> {

    override fun mapFromApiModel(apiModel: Pokemon): PokemonEntity {
        val id = apiModel.url.split("/")[apiModel.url.split("/").size - 2].toInt()
        return PokemonEntity(
            id,
            apiModel.name,
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png"
        )
    }

    override fun mapFromApiModelList(apiModelList: List<Pokemon>): List<PokemonEntity>  {
        return apiModelList.map { pokemon ->
            mapFromApiModel(pokemon)
        }
    }
}