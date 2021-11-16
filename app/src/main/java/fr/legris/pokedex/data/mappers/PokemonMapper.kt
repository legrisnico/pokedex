package fr.legris.pokedex.data.mappers

import fr.legris.pokedex.data.api.model.PokemonDTO
import fr.legris.pokedex.data.api.model.TypesDTO
import fr.legris.pokedex.data.bdd.model.PokemonEntity
import fr.legris.pokedex.data.bdd.model.TypeEntity
import fr.legris.pokedex.ui.model.Pokemon
import fr.legris.pokedex.ui.model.Type

class PokemonMapper : DbEntityMapper<PokemonEntity, PokemonDTO, Pokemon> {
    override fun mapFromApiModel(apiModel: PokemonDTO): PokemonEntity {
        return PokemonEntity(
            apiModel.id,
            apiModel.name,
            apiModel.spritesDTO.front_default,
            apiModel.types.map { mapTypeDTOToTypeEntity(it) }
        )
    }

    override fun mapFromApiModelList(apiModelList: List<PokemonDTO>): List<PokemonEntity> {
        return apiModelList.map {
            mapFromApiModel(it)
        }
    }

    override fun mapFromDbEntityToModelUi(dbEntity: PokemonEntity): Pokemon {
        val name = dbEntity.name.replaceFirstChar(Char::titlecase)
        return Pokemon(
            dbEntity.id,
            name,
            dbEntity.mainPictureUrl,
            dbEntity.typeEntities.map { mapTypeEntityToModelUi(it) }
        )
    }

    private fun mapTypeDTOToTypeEntity(typesDto : TypesDTO) : TypeEntity{
        return TypeEntity(
            typesDto.slot,
            typesDto.typeDTO.name
        )
    }

    private fun mapTypeEntityToModelUi(typeEntity: TypeEntity) : Type{
        return Type.values().firstOrNull { typeEnum -> typeEntity.name == typeEnum.name } ?: Type.UNKNOWN
    }
}