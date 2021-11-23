package fr.legris.pokedex.data.bdd.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "pokemonEntity")
@TypeConverters(Converters::class)
data class PokemonEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "main_picture_url")
    val mainPictureUrl: String,
    @ColumnInfo(name = "types")
    val typeEntities: List<TypeEntity>? = null,
    @ColumnInfo(name = "weight")
    val weight: Float? = null,
    @ColumnInfo(name = "height")
    val height: Float? = null,
    @ColumnInfo(name = "base_experience")
    val baseExperience: Int? = null,
) : DbEntity
