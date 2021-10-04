package fr.legris.pokedex.data.mappers

interface DbEntityMapper<DbEntity, ApiModel> {
    /*fun mapToApiModel(model: DbEntity): ApiModel*/
    /*fun mapToApiModelList(modelList: List<DbEntity>): List<ApiModel>*/
    fun mapFromApiModel(apiModel: ApiModel): DbEntity
    fun mapFromApiModelList(apiModelList: List<ApiModel>): List<DbEntity>
}