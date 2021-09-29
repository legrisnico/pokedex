package fr.legris.pokedex.data.api.model

import com.google.gson.annotations.SerializedName

data class Moves (

        @SerializedName("move") val move : Move,
        @SerializedName("version_group_details") val versionGroupDetails : List<VersionGroupDetails>
)