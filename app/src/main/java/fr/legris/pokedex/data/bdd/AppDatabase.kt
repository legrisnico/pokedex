package fr.legris.pokedex.data.bdd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fr.legris.pokedex.data.bdd.dao.PokemonDao
import fr.legris.pokedex.data.bdd.dao.PokemonFromListDao
import fr.legris.pokedex.data.bdd.model.PokemonEntity
import fr.legris.pokedex.data.bdd.model.PokemonFromListEntity

@Database(entities = [PokemonFromListEntity::class, PokemonEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonFromListDao(): PokemonFromListDao
    abstract fun pokemonDao(): PokemonDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "pokemon-database")
                .fallbackToDestructiveMigration()
                .build()
    }
}
