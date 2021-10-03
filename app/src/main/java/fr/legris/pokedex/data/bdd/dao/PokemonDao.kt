package fr.legris.pokedex.data.bdd.dao

import androidx.paging.PagingSource
import androidx.room.*
import fr.legris.pokedex.data.bdd.model.PokemonEntity

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemonEntity")
    suspend fun getAll(): List<PokemonEntity>

    @Query("SELECT * FROM pokemonEntity")
    fun pagingSource(): PagingSource<Int, PokemonEntity>

    @Query("SELECT * FROM pokemonEntity WHERE id IN (:userIds)")
    suspend fun loadAllByIds(userIds: IntArray): List<PokemonEntity>

    @Query("SELECT * FROM pokemonEntity WHERE name LIKE :name LIMIT 1")
    suspend fun findByName(name : String): PokemonEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pokemonList: List<PokemonEntity>)

    @Delete
    suspend fun delete(pokemon: PokemonEntity)

    @Query("DELETE FROM pokemonEntity")
    suspend fun deleteAll()
}

