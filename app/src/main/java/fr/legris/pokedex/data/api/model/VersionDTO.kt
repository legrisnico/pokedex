package fr.legris.pokedex.data.api.model

import com.google.gson.annotations.SerializedName

data class VersionDTO (

	@SerializedName("name") val name : String,
	@SerializedName("url") val url : String
)