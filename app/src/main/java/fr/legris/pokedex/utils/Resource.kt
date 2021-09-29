package fr.legris.pokedex.utils

sealed class Resource<out T> {
    data class Success<out T>(val value: T): Resource<T>()
    data class Error(val code: Int, val msg: String): Resource<Nothing>()
    data class Loading(val progression : Int, val pokemonImageUrl : String) : Resource<Nothing>()
    object NetworkError: Resource<Nothing>()
}