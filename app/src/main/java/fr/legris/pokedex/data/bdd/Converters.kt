package fr.legris.pokedex.data.bdd

import androidx.room.TypeConverter
import fr.legris.pokedex.data.bdd.model.TypeEntity
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString

class Converters {
    @TypeConverter
    fun fromList(value : List<TypeEntity>) = Json.encodeToString(value)

    @TypeConverter
    fun toList(value: String) = Json.decodeFromString<List<TypeEntity>>(value)
}