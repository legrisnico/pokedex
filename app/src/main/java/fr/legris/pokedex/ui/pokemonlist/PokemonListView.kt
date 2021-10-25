package fr.legris.pokedex.ui.pokemonlist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import fr.legris.pokedex.R
import fr.legris.pokedex.ui.model.Pokemon
import fr.legris.pokedex.ui.theme.PokedexTheme
import kotlinx.coroutines.flow.Flow

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun PokemonListView(navController: NavController, pokemonFlow: Flow<PagingData<Pokemon>>) {
    val lazyPokemonItems: LazyPagingItems<Pokemon> = pokemonFlow.collectAsLazyPagingItems()
    LazyVerticalGrid(
        cells = GridCells.Fixed(4)
    ) {
        items(lazyPokemonItems.itemCount) { index ->
            lazyPokemonItems[index]?.let {
                Pokemon(navController, it)
            }
        }
    }

}

@ExperimentalCoilApi
@Composable
fun Pokemon(navController: NavController, pokemon: Pokemon) {
    PokedexTheme {
        Column(
            modifier = Modifier
                .height(128.dp)
                .padding(4.dp)
                .clickable(
                    onClick = { navController.navigate("pokemonDetail") }
                ),
            verticalArrangement = Arrangement.Bottom
        ) {
            Image(
                painter = rememberImagePainter(
                    data = pokemon.mainPictureUrl,
                    builder = {
                        crossfade(true)
                        placeholder(R.drawable.ic_pokemon_placeholder)
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
                text = pokemon.name,

                )
        }

    }
}
