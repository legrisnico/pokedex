package fr.legris.pokedex.data.api.model

import com.google.gson.annotations.SerializedName

data class Abilities (

	@SerializedName("is_hidden") val isHidden : Boolean,
	@SerializedName("slot") val slot : Int,
	@SerializedName("ability") val ability : Ability
)