package fr.legris.pokedex.data.mappers

import fr.legris.pokedex.data.api.model.PokemonDTO
import fr.legris.pokedex.data.bdd.model.PokemonEntity
import fr.legris.pokedex.ui.model.Pokemon

class PokemonMapper : DbEntityMapper<PokemonEntity, PokemonDTO, Pokemon> {

    private val typeMapper = TypeMapper()

    override fun mapFromApiModel(apiModel: PokemonDTO): PokemonEntity {
        return PokemonEntity(
            apiModel.id,
            apiModel.name,
            apiModel.spritesDTO.front_default,
            apiModel.types.map { typeMapper.mapFromApiModel(it) }
        )
    }

    override fun mapFromDbEntityToModelUi(dbEntity: PokemonEntity): Pokemon {
        val name = dbEntity.name.replaceFirstChar(Char::titlecase)
        return Pokemon(
            dbEntity.id,
            name,
            dbEntity.mainPictureUrl,
            dbEntity.typeEntities.map { typeMapper.mapFromDbEntityToModelUi(it) }
        )
    }
}
