package fr.legris.pokedex.data.mappers

import androidx.compose.ui.text.intl.Locale
import fr.legris.pokedex.data.api.model.PokemonFromListDTO
import fr.legris.pokedex.data.bdd.model.PokemonEntity
import fr.legris.pokedex.ui.model.PokemonFromList

class PokemonListMapper: DbEntityMapper<PokemonEntity, PokemonFromListDTO, PokemonFromList> {

    override fun mapFromApiModel(apiModel: PokemonFromListDTO): PokemonEntity {
        val id = apiModel.url.split("/")[apiModel.url.split("/").size - 2].toInt()
        return PokemonEntity(
            id = id,
            name = apiModel.name,
            mainPictureUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png"
        )
    }

    override fun mapFromApiModelList(apiModelList: List<PokemonFromListDTO>): List<PokemonEntity>  {
        return apiModelList.map { pokemon ->
            mapFromApiModel(pokemon)
        }
    }

    override fun mapFromDbEntityToModelUi(dbEntity: PokemonEntity): PokemonFromList {
        val name = dbEntity.name.replaceFirstChar(Char::titlecase)
        return PokemonFromList(
            dbEntity.id,
            name,
            dbEntity.mainPictureUrl
        )
    }
}