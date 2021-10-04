package fr.legris.pokedex.data.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import fr.legris.pokedex.BuildConfig
import fr.legris.pokedex.data.api.PokemonService
import fr.legris.pokedex.data.bdd.AppDatabase
import fr.legris.pokedex.data.bdd.dao.PokemonDao
import fr.legris.pokedex.data.bdd.model.PokemonEntity
import fr.legris.pokedex.data.mappers.PokemonMapper
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeUnit


@ExperimentalPagingApi
class PokemonListRemoteMediator(
    private val pokemonDao: PokemonDao,
    private val pokemonService: PokemonService
) : RemoteMediator<Int, PokemonEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = false
                        )

                    lastItem.id
                }
            }

            val response = pokemonService.listPokemon(loadKey, BuildConfig.POKEMON_PAGE_SIZE)

            if (loadType == LoadType.REFRESH) {
                pokemonDao.deleteAll()
            }

            pokemonDao.insertAll(
                PokemonMapper().mapFromApiModelList(response.results)
            )


            MediatorResult.Success(
                endOfPaginationReached = response.next == null
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }



}
