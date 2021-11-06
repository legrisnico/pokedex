package fr.legris.pokedex.data.api.model

import com.google.gson.annotations.SerializedName

data class HeldItemsDTO(

    @SerializedName("item") val itemDTO: ItemDTO,
    @SerializedName("version_details") val versionDetails: List<VersionDetails>
)
