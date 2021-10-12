package fr.legris.pokedex.data.api.model

import com.google.gson.annotations.SerializedName

data class TypesDTO (

	@SerializedName("slot") val slot : Int,
	@SerializedName("type") val typeDTO : TypeDTO
)