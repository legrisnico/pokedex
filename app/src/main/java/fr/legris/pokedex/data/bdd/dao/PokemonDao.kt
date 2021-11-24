package fr.legris.pokedex.data.bdd.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import fr.legris.pokedex.data.bdd.model.Converters
import fr.legris.pokedex.data.bdd.model.PokemonEntity

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemonEntity WHERE id LIKE :id")
    fun findById(id : Int): LiveData<PokemonEntity>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemon: PokemonEntity)

    @Delete
    suspend fun delete(pokemon: PokemonEntity)

    @Query("DELETE FROM pokemonEntity")
    suspend fun deleteAll()

    @Query("SELECT COUNT(*) FROM pokemonEntity WHERE id LIKE :id AND detail_complete LIKE 1")
    suspend fun exist(id: Int): Boolean
}

