package fr.legris.pokedex.data.api

import fr.legris.pokedex.data.api.model.Pokemon
import fr.legris.pokedex.data.api.model.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PokemonService {

    @GET("pokemon")
    suspend fun listPokemon(
            @Query("offset") offset : Int,
            @Query("limit") limit : Int
    ): PokemonList

    @GET("pokemon/{id}/")
    suspend fun getPokemonById(
        @Path("id") id : Int
    ) : Pokemon
}