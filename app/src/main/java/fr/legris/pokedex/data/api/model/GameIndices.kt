package fr.legris.pokedex.data.api.model

import com.google.gson.annotations.SerializedName

data class GameIndices (

	@SerializedName("game_index") val gameIndex : Int,
	@SerializedName("version") val version : Version
)