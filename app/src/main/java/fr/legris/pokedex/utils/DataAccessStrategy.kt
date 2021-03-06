package fr.legris.pokedex.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import fr.legris.pokedex.data.bdd.model.PokemonEntity
import fr.legris.pokedex.data.mappers.DbEntityMapper
import kotlinx.coroutines.Dispatchers

fun <T, A, M> performGetOperation(
    databaseQuery: () -> LiveData<T>,
    existInDbQuery: suspend () -> Boolean,
    networkCall: suspend () -> Resource<A>,
    saveCallResult: suspend (A) -> Unit,
    mapper: DbEntityMapper<T, A, M>
): LiveData<Resource<M>> =

    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val source = databaseQuery.invoke().map {
            Resource.success(mapper.mapFromDbEntityToModelUi(it))
        }

        emitSource(source)

        val pokemonAlreadyLoaded = existInDbQuery.invoke()
        if(!pokemonAlreadyLoaded) {
            val responseStatus = networkCall.invoke()
            if (responseStatus.status == Resource.Status.SUCCESS) {
                saveCallResult(responseStatus.data!!)
            } else if (responseStatus.status == Resource.Status.ERROR) {
                emit(Resource.error(responseStatus.message!!))
                emitSource(source)
            }
        }
    }