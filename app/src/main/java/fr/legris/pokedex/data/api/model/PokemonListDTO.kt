package fr.legris.pokedex.data.api.model

import com.google.gson.annotations.SerializedName

data class PokemonListDTO(
    @SerializedName("count") val count : Int,
    @SerializedName("next") val next : String?,
    @SerializedName("previous") val previous : String?,
    @SerializedName("results") val results : List<PokemonDTO>
) : ApiModel
