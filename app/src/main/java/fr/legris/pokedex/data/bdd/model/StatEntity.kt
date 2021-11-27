package fr.legris.pokedex.data.bdd.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "statEntity")
data class StatEntity(
    @ColumnInfo(name = "base_stat")
    val statValue : Int,
    @ColumnInfo(name = "stat_name")
    val statName : String
) : DbEntity
