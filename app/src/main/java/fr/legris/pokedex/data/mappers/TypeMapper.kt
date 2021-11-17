package fr.legris.pokedex.data.mappers

import fr.legris.pokedex.data.api.model.TypesDTO
import fr.legris.pokedex.data.bdd.model.TypeEntity
import fr.legris.pokedex.ui.model.Type

class TypeMapper : DbEntityMapper<TypeEntity, TypesDTO, Type> {

    override fun mapFromApiModel(apiModel: TypesDTO): TypeEntity {
        return TypeEntity(
            apiModel.slot,
            apiModel.typeDTO.name
        )
    }

    override fun mapFromDbEntityToModelUi(dbEntity: TypeEntity): Type {
        return Type.values().firstOrNull { typeEnum -> dbEntity.name == typeEnum.name }
            ?: Type.UNKNOWN
    }
}