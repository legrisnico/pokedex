package fr.legris.pokedex.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import dagger.hilt.android.AndroidEntryPoint
import fr.legris.pokedex.ui.pokemondetail.PokemonDetailView
import fr.legris.pokedex.ui.pokemonlist.PokemonListView
import fr.legris.pokedex.ui.theme.PokedexTheme
import fr.legris.pokedex.utils.Constants.ARG_POKEMON_ID

@ExperimentalCoilApi
@ExperimentalPagingApi
@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PokedexTheme {
                val navController = rememberNavController()

                Surface {
                    NavHost(navController = navController, startDestination = NAV_POKEMON_LIST) {
                        composable(NAV_POKEMON_LIST) { PokemonListView(navController) }
                        composable(
                            "$NAV_POKEMON_DETAIL/{$ARG_POKEMON_ID}",
                            listOf(navArgument(ARG_POKEMON_ID) { type = NavType.IntType })
                        ) {
                            PokemonDetailView()
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val NAV_POKEMON_LIST = "pokemonList"
        const val NAV_POKEMON_DETAIL = "pokemonDetail"
    }
}
