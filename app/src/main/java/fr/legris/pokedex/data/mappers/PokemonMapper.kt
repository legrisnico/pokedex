package fr.legris.pokedex.data.mappers

import fr.legris.pokedex.data.api.model.PokemonDTO
import fr.legris.pokedex.data.bdd.model.PokemonEntity
import fr.legris.pokedex.ui.model.Pokemon

class PokemonMapper : DbEntityMapper<PokemonEntity, PokemonDTO, Pokemon> {
    override fun mapFromApiModel(apiModel: PokemonDTO): PokemonEntity {
        return PokemonEntity(
            apiModel.id,
            apiModel.name
        )
    }

    override fun mapFromApiModelList(apiModelList: List<PokemonDTO>): List<PokemonEntity> {
        return apiModelList.map {
            mapFromApiModel(it)
        }
    }

    override fun mapFromDbEntityToModelUi(dbEntity: PokemonEntity?): Pokemon {
        return Pokemon(
            dbEntity?.id ?: 0,
            dbEntity?.name ?: ""
        )
    }
}
