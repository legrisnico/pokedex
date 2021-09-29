package fr.legris.pokedex.data.api.model

import com.google.gson.annotations.SerializedName

data class HeldItems (

        @SerializedName("item") val item : Item,
        @SerializedName("version_details") val versionDetails : List<VersionDetails>
)