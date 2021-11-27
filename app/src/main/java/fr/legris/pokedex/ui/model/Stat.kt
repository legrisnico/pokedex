package fr.legris.pokedex.ui.model

import androidx.room.ColumnInfo

data class Stat(
    val statValue : Int,
    val statName : String
) : ModelUi
