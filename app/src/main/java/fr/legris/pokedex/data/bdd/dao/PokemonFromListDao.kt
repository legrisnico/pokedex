package fr.legris.pokedex.data.bdd.dao

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*
import fr.legris.pokedex.data.bdd.model.PokemonFromListEntity

@Dao
interface PokemonFromListDao {

    @Query("SELECT * FROM pokemonFromListEntity")
    fun pagingSource(): PagingSource<Int, PokemonFromListEntity>

    @Query("SELECT * FROM pokemonFromListEntity WHERE id LIKE :id LIMIT 1")
    fun findById(id: Int): LiveData<PokemonFromListEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pokemonList: List<PokemonFromListEntity>)

    @Delete
    suspend fun delete(pokemon: PokemonFromListEntity)

    @Query("DELETE FROM pokemonFromListEntity")
    suspend fun deleteAll()
}
