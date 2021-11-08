package fr.legris.pokedex.data.bdd.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import fr.legris.pokedex.data.bdd.model.PokemonEntity

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemonEntity WHERE id LIKE :id LIMIT 1")
    fun findById(id : Int): LiveData<PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pokemonList: List<PokemonEntity>)

    @Delete
    suspend fun delete(pokemon: PokemonEntity)

    @Query("DELETE FROM pokemonEntity")
    suspend fun deleteAll()
}

