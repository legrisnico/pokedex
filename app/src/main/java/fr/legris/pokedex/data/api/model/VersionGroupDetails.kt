package fr.legris.pokedex.data.api.model

import com.google.gson.annotations.SerializedName

data class VersionGroupDetails (

		@SerializedName("level_learned_at") val levelLearnedAt : Int,
		@SerializedName("version_group") val versionGroup : VersionGroup,
		@SerializedName("move_learn_method") val moveLearnMethod : MoveLearnMethod
)