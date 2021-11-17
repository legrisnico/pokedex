package fr.legris.pokedex.data.bdd.model

import androidx.room.ColumnInfo
import kotlinx.serialization.Serializable

@Serializable
data class TypeEntity(
    @ColumnInfo(name = "slot") val slot : Int,
    @ColumnInfo(name = "name") val name : String
) : DbEntity
