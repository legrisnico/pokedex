package fr.legris.pokedex.data.mappers

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import fr.legris.pokedex.data.api.model.PokemonFromListDTO
import fr.legris.pokedex.data.bdd.model.PokemonFromListEntity
import fr.legris.pokedex.ui.model.PokemonFromList

class PokemonListMapper: DbEntityMapper<PokemonFromListEntity, PokemonFromListDTO, PokemonFromList> {

    override fun mapFromApiModel(apiModel: PokemonFromListDTO): PokemonFromListEntity {
        val id = apiModel.url.split("/")[apiModel.url.split("/").size - 2].toInt()
        return PokemonFromListEntity(
            id,
            apiModel.name,
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png"
        )
    }

    override fun mapFromApiModelList(apiModelList: List<PokemonFromListDTO>): List<PokemonFromListEntity>  {
        return apiModelList.map { pokemon ->
            mapFromApiModel(pokemon)
        }
    }

    override fun mapFromDbEntityToModelUi(dbEntity: PokemonFromListEntity): PokemonFromList {
        val name = dbEntity.name.capitalize(Locale.current)
        return PokemonFromList(
            dbEntity.id ?: 0,
            name ?: "",
            dbEntity.mainPictureUrl ?: ""
        )
    }
}