package fr.legris.pokedex.data.bdd.dao

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*
import fr.legris.pokedex.data.bdd.model.PokemonEntity

@Dao
interface PokemonFromListDao {

    @Query("SELECT * FROM pokemonEntity")
    fun pagingSource(): PagingSource<Int, PokemonEntity>

    @Query("SELECT * FROM pokemonEntity WHERE id LIKE :id LIMIT 1")
    fun findById(id : Int): LiveData<PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(pokemonList: List<PokemonEntity>)

    @Delete
    suspend fun delete(pokemon: PokemonEntity)

    @Query("DELETE FROM pokemonEntity")
    suspend fun deleteAll()
}

