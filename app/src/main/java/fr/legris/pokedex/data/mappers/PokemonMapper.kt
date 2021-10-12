package fr.legris.pokedex.data.mappers

import fr.legris.pokedex.data.api.model.PokemonDTO
import fr.legris.pokedex.data.bdd.model.DbEntity
import fr.legris.pokedex.data.bdd.model.PokemonEntity
import fr.legris.pokedex.ui.model.Pokemon

class PokemonMapper: DbEntityMapper<PokemonEntity, PokemonDTO, Pokemon> {

    override fun mapFromApiModel(apiModel: PokemonDTO): PokemonEntity {
        val id = apiModel.url.split("/")[apiModel.url.split("/").size - 2].toInt()
        return PokemonEntity(
            id,
            apiModel.name,
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png"
        )
    }

    override fun mapFromApiModelList(apiModelList: List<PokemonDTO>): List<PokemonEntity>  {
        return apiModelList.map { pokemon ->
            mapFromApiModel(pokemon)
        }
    }

    override fun mapFromDbEntityToModelUi(dbEntity: PokemonEntity): Pokemon {
        return Pokemon(
            dbEntity.id,
            dbEntity.name,
            dbEntity.mainPictureUrl
        )
    }
}