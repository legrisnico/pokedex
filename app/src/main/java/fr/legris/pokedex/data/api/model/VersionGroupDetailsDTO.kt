package fr.legris.pokedex.data.api.model

import com.google.gson.annotations.SerializedName

data class VersionGroupDetailsDTO(

    @SerializedName("level_learned_at") val levelLearnedAt: Int,
    @SerializedName("version_group") val versionGroupDTO: VersionGroupDTO,
    @SerializedName("move_learn_method") val moveLearnMethodDTO: MoveLearnMethodDTO
)
