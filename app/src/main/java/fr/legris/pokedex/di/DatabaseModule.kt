package fr.legris.pokedex.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fr.legris.pokedex.data.bdd.AppDatabase
import fr.legris.pokedex.data.bdd.dao.PokemonDao
import fr.legris.pokedex.data.bdd.dao.PokemonFromListDao

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideAppDataBase(@ApplicationContext context: Context): AppDatabase = AppDatabase.getDatabase(context)

    @Provides
    fun providePokemonFromListDao(db: AppDatabase): PokemonFromListDao = db.pokemonFromListDao()

    @Provides
    fun providePokemonDao(db: AppDatabase): PokemonDao = db.pokemonDao()
}
