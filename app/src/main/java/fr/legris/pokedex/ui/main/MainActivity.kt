package fr.legris.pokedex.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.rememberNavController
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import dagger.hilt.android.AndroidEntryPoint
import fr.legris.pokedex.ui.pokemondetail.PokeDetailViewModel
import fr.legris.pokedex.ui.pokemonlist.PokeListViewModel
import fr.legris.pokedex.ui.pokemonlist.PokemonList
import fr.legris.pokedex.ui.theme.PokedexTheme

@ExperimentalCoilApi
@ExperimentalPagingApi
@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val pokeListViewModel: PokeListViewModel by viewModels()
    private val pokeDetailViewModel: PokeDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PokedexTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    PokemonList(pokeListViewModel.pokemonFlow)
                }
            }
        }
    }
}

