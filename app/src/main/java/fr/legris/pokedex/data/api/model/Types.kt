package fr.legris.pokedex.data.api.model

import com.google.gson.annotations.SerializedName

data class Types (

	@SerializedName("slot") val slot : Int,
	@SerializedName("type") val type : Type
)