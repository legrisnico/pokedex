package fr.legris.pokedex.di

import android.content.Context
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.legris.pokedex.data.bdd.AppDatabase
import fr.legris.pokedex.data.bdd.dao.PokemonDao

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun provideAppDataBase(context : Context): AppDatabase = AppDatabase.getDatabase(context)

    @Provides
    fun providePokemonDao(db : AppDatabase): PokemonDao = db.pokemonDao()
}