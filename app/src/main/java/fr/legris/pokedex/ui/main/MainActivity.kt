package fr.legris.pokedex.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import dagger.hilt.android.AndroidEntryPoint
import fr.legris.pokedex.R
import fr.legris.pokedex.ui.model.Pokemon
import fr.legris.pokedex.ui.theme.PokedexTheme
import kotlinx.coroutines.flow.Flow

@ExperimentalCoilApi
@ExperimentalPagingApi
@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PokedexTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    PokemonList(viewModel.pokemonFlow)
                }
            }
        }
    }
}

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun PokemonList(pokemonFlow: Flow<PagingData<Pokemon>>) {
    val lazyPokemonItems: LazyPagingItems<Pokemon> = pokemonFlow.collectAsLazyPagingItems()
    LazyVerticalGrid(
        cells = GridCells.Fixed(4)
    ) {
        items(lazyPokemonItems.itemCount) { index ->
            lazyPokemonItems[index]?.let {
                Pokemon(it)
            }
        }
    }

}

@ExperimentalCoilApi
@Composable
fun Pokemon(pokemon: Pokemon) {
    PokedexTheme {
        Column(
            modifier = Modifier
                .height(128.dp)
                .clickable(
                    onClick = { /* TODO : go to pokemon details */ }
                ),
            verticalArrangement = Arrangement.Bottom
        ) {
            Image(
                painter = rememberImagePainter(
                    data = pokemon.mainPictureUrl,
                    builder = {
                        crossfade(true)
                        placeholder(R.drawable.ic_baseline_help_outline_24)
                    }
                ),
                contentDescription = "Image de ${pokemon.name}",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.70f)
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = pokemon.name
            )
        }

    }
}