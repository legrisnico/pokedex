package fr.legris.pokedex.data.bdd.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun typeEntityFromString(value: String?): List<TypeEntity>? {
        val itemType = object : TypeToken<List<TypeEntity>>() {}.type
        return value?.let { Gson().fromJson(it, itemType) }
    }

    @TypeConverter
    fun typeEntityToString(type: List<TypeEntity>?): String? {
        return Gson().toJson(type)
    }

    @TypeConverter
    fun statEntityFromString(value: String?): List<StatEntity>? {
        val itemType = object : TypeToken<List<StatEntity>>() {}.type
        return value?.let { Gson().fromJson(it, itemType) }
    }

    @TypeConverter
    fun statEntityToString(type: List<StatEntity>?): String? {
        return Gson().toJson(type)
    }
}
