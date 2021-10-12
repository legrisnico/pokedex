package fr.legris.pokedex.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import fr.legris.pokedex.data.api.PokemonService
import fr.legris.pokedex.data.bdd.dao.PokemonDao
import fr.legris.pokedex.data.repository.PokemonRepository
import fr.legris.pokedex.data.repository.PokemonRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun providePokemonRepository(
        pokemonDao: PokemonDao,
        pokemonService: PokemonService
    ): PokemonRepository =
        PokemonRepositoryImpl(pokemonDao, pokemonService)
}