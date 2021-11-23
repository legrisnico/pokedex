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
            id = apiModel.id,
            name = apiModel.name,
            mainPictureUrl = apiModel.spritesDTO.front_default,
            typeEntities = apiModel.types.map { mapTypeDTOToTypeEntity(it) },
            weight = apiModel.weight,
            height = apiModel.height,
            baseExperience = apiModel.baseExperience
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
            id = dbEntity.id,
            name = name,
            mainPictureUrl = dbEntity.mainPictureUrl,
            types = dbEntity.typeEntities?.map { mapTypeEntityToModelUi(it) } ?: listOf(),
            weight = dbEntity.weight ?: 0,
            height = dbEntity.height ?: 0,
            baseExperience = dbEntity.baseExperience ?: 0
        )
    }

    private fun mapTypeDTOToTypeEntity(typesDto : TypesDTO) : TypeEntity{
        return TypeEntity(
            slot = typesDto.slot,
            name = typesDto.typeDTO.name
        )
    }

    private fun mapTypeEntityToModelUi(typeEntity: TypeEntity) : Type{
        return Type.values().firstOrNull { typeEnum -> typeEntity.name == typeEnum.typeName } ?: Type.UNKNOWN
    }
}