package fr.legris.pokedex.data.api.model

import com.google.gson.annotations.SerializedName

data class Stats (

	@SerializedName("base_stat") val baseStat : Int,
	@SerializedName("effort") val effort : Int,
	@SerializedName("stat") val stat : Stat
)