package fr.legris.pokedex.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.*
import fr.legris.pokedex.BuildConfig
import fr.legris.pokedex.data.api.PokemonService
import fr.legris.pokedex.data.api.model.PokemonDTO
import fr.legris.pokedex.data.bdd.dao.PokemonDao
import fr.legris.pokedex.data.bdd.dao.PokemonFromListDao
import fr.legris.pokedex.data.mappers.PokemonListMapper
import fr.legris.pokedex.data.mappers.PokemonMapper
import fr.legris.pokedex.data.repository.paging.PokemonListRemoteMediator
import fr.legris.pokedex.ui.model.Pokemon
import fr.legris.pokedex.ui.model.PokemonFromList
import fr.legris.pokedex.utils.Resource
import fr.legris.pokedex.utils.performGetOperation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonFromListDao: PokemonFromListDao,
    private val pokemonDao: PokemonDao,
    private val pokemonService: PokemonService
) : PokemonRepository {

    @ExperimentalPagingApi
    override fun getPokemonList(): Flow<PagingData<PokemonFromList>> = Pager(
        config = PagingConfig(pageSize = BuildConfig.POKEMON_PAGE_SIZE),
        remoteMediator = PokemonListRemoteMediator(pokemonFromListDao, pokemonService)
    ) {
        pokemonFromListDao.pagingSource()
    }.flow.map { pagingData ->
        pagingData.map { pokemonEntity ->
            PokemonListMapper().mapFromDbEntityToModelUi(pokemonEntity)
        }
    }

    override fun getPokemonById(id: Int): LiveData<Resource<Pokemon>> {
        return performGetOperation(
        databaseQuery = { pokemonDao.findById(id) },
        networkCall = { getResult { pokemonService.getPokemonById(id) } },
        saveCallResult = { pokemonDao.insertAll(listOf(PokemonMapper().mapFromApiModel(it))) },
        mapper = PokemonMapper()
    )}

    private suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }
}