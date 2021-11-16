package fr.legris.pokedex.data.bdd.model

import androidx.room.ColumnInfo

data class TypeEntity(
    @ColumnInfo(name = "slot") val slot : Int,
    @ColumnInfo(name = "name") val name : String
) : DbEntity
