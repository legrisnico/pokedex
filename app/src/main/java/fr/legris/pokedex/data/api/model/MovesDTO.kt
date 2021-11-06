package fr.legris.pokedex.data.api.model

import com.google.gson.annotations.SerializedName

data class MovesDTO(

    @SerializedName("move") val moveDTO: MoveDTO,
    @SerializedName("version_group_details") val versionGroupDetailDTOS: List<VersionGroupDetailsDTO>
)
