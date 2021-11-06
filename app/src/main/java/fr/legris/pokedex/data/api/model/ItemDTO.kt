package fr.legris.pokedex.data.api.model

import com.google.gson.annotations.SerializedName

data class ItemDTO(

    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)
