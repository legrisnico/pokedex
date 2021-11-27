package fr.legris.pokedex.data.mappers

import fr.legris.pokedex.data.api.model.PokemonDTO
import fr.legris.pokedex.data.api.model.StatDTO
import fr.legris.pokedex.data.api.model.StatsDTO
import fr.legris.pokedex.data.api.model.TypesDTO
import fr.legris.pokedex.data.bdd.model.PokemonEntity
import fr.legris.pokedex.data.bdd.model.StatEntity
import fr.legris.pokedex.data.bdd.model.TypeEntity
import fr.legris.pokedex.ui.model.Pokemon
import fr.legris.pokedex.ui.model.Stat
import fr.legris.pokedex.ui.model.Type

class PokemonMapper : DbEntityMapper<PokemonEntity, PokemonDTO, Pokemon> {
    override fun mapFromApiModel(apiModel: PokemonDTO): PokemonEntity {
        return PokemonEntity(
            id = apiModel.id,
            detailComplete = true,
            name = apiModel.name,
            mainPictureUrl = apiModel.spritesDTO.front_default,
            types = apiModel.types.map { mapTypeDTOToTypeEntity(it) },
            stats = apiModel.stats.map { mapStatDTOToStatEntity(it) },
            weight = apiModel.weight*0.1f,
            height = apiModel.height*0.1f,
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
            types = dbEntity.types?.map { mapTypeEntityToModelUi(it) } ?: listOf(),
            stats = dbEntity.stats?.map { mapStatEntityToModelUi(it) } ?: listOf(),
            weight = dbEntity.weight ?: 0.0f,
            height = dbEntity.height ?: 0.0f,
            baseExperience = dbEntity.baseExperience ?: 0
        )
    }

    private fun mapTypeDTOToTypeEntity(typesDto : TypesDTO) : TypeEntity{
        return TypeEntity(
            slot = typesDto.slot,
            name = typesDto.typeDTO.name
        )
    }

    private fun mapStatDTOToStatEntity(statsDTO: StatsDTO) : StatEntity{
        return StatEntity(
            statsDTO.baseStat,
            statsDTO.statDTO.name
        )
    }

    private fun mapTypeEntityToModelUi(typeEntity: TypeEntity) : Type{
        return Type.values().firstOrNull { typeEnum -> typeEntity.name == typeEnum.typeName } ?: Type.UNKNOWN
    }

    private fun mapStatEntityToModelUi(statEntity: StatEntity) : Stat {
        return Stat(statName = statEntity.statName, statValue = statEntity.statValue)
    }
}