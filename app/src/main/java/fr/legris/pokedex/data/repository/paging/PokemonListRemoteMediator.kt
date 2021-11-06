package fr.legris.pokedex.data.repository.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import fr.legris.pokedex.BuildConfig
import fr.legris.pokedex.data.api.PokemonService
import fr.legris.pokedex.data.api.model.PokemonListDTO
import fr.legris.pokedex.data.bdd.dao.PokemonFromListDao
import fr.legris.pokedex.data.bdd.model.PokemonFromListEntity
import fr.legris.pokedex.data.mappers.PokemonListMapper
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class PokemonListRemoteMediator(
    private val pokemonFromListDao: PokemonFromListDao,
    private val pokemonService: PokemonService
) : RemoteMediator<Int, PokemonFromListEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonFromListEntity>
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

            if (!response.isSuccessful) {
                MediatorResult.Error(HttpException(response))
            }

            val result = response.body() ?: PokemonListDTO(0, null, null, listOf())

            if (loadType == LoadType.REFRESH) {
                pokemonFromListDao.deleteAll()
            }

            pokemonFromListDao.insertAll(
                PokemonListMapper().mapFromApiModelList(result.results)
            )

            MediatorResult.Success(
                endOfPaginationReached = result.next == null
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}
