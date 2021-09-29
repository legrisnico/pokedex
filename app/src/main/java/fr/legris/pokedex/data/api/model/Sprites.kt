package fr.legris.pokedex.data.api.model

import com.google.gson.annotations.SerializedName

data class Sprites (

	@SerializedName("back_female") val back_female : String,
	@SerializedName("back_shiny_female") val back_shiny_female : String,
	@SerializedName("back_default") val back_default : String,
	@SerializedName("front_female") val front_female : String,
	@SerializedName("front_shiny_female") val front_shiny_female : String,
	@SerializedName("back_shiny") val back_shiny : String,
	@SerializedName("front_default") val front_default : String,
	@SerializedName("front_shiny") val front_shiny : String,
)