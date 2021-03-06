package fr.legris.pokedex.data.mappers

interface DbEntityMapper<DbEntity, ApiModel, ModelUi> {
    fun mapFromApiModel(apiModel: ApiModel): DbEntity
    fun mapFromApiModelList(apiModelList: List<ApiModel>): List<DbEntity>
    fun mapFromDbEntityToModelUi(dbEntity: DbEntity): ModelUi
}