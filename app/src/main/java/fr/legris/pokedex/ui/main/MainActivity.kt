package fr.legris.pokedex.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
import fr.legris.pokedex.data.bdd.model.PokemonEntity
import fr.legris.pokedex.ui.theme.PokedexTheme
import kotlinx.coroutines.flow.Flow

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()


    @ExperimentalPagingApi
    @ExperimentalCoilApi
    @ExperimentalFoundationApi
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
fun PokemonList(pokemonFlow: Flow<PagingData<PokemonEntity>>) {
    val lazyPokemonItems: LazyPagingItems<PokemonEntity> = pokemonFlow.collectAsLazyPagingItems()
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
fun Pokemon(pokemon: PokemonEntity) {
    PokedexTheme {
        Column(
            modifier = Modifier.height(128.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Image(
                painter = rememberImagePainter(pokemon.mainPictureUrl),
                contentDescription = "Image de ${pokemon.name}",
                modifier = Modifier.fillMaxWidth().fillMaxHeight(0.70f)
                )
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = pokemon.name
            )
        }

    }
}