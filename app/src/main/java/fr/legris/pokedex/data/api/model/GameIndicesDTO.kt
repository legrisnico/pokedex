package fr.legris.pokedex.data.api.model

import com.google.gson.annotations.SerializedName

data class GameIndicesDTO(

    @SerializedName("game_index") val gameIndex: Int,
    @SerializedName("version") val versionDTO: VersionDTO
)
