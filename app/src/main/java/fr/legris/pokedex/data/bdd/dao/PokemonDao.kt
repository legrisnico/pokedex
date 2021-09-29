package fr.legris.pokedex.data.bdd.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import fr.legris.pokedex.data.bdd.model.PokemonEntity

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemonEntity")
    fun getAll(): List<PokemonEntity>

    @Query("SELECT * FROM pokemonEntity WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<PokemonEntity>

    @Query("SELECT * FROM pokemonEntity WHERE name LIKE :name LIMIT 1")
    fun findByName(name : String): PokemonEntity

    @Insert
    fun insertAll(vararg users: PokemonEntity)

    @Delete
    fun delete(user: PokemonEntity)
}

