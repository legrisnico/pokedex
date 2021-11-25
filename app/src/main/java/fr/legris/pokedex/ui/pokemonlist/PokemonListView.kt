package fr.legris.pokedex.ui.pokemonlist

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.request.CachePolicy
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import fr.legris.pokedex.R
import fr.legris.pokedex.ui.common.PokeballRefreshIndicator
import fr.legris.pokedex.ui.main.MainActivity
import fr.legris.pokedex.ui.model.PokemonFromList
import fr.legris.pokedex.ui.theme.PokedexTheme

@ExperimentalPagingApi
@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun PokemonListView(
    navController: NavController,
    viewModel: PokemonListViewModel = hiltViewModel()
) {

    val lazyPokemonItems: LazyPagingItems<PokemonFromList> =
        viewModel.pokemonFlow.collectAsLazyPagingItems()
    val isRefreshing = lazyPokemonItems.loadState.refresh is LoadState.Loading

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = { lazyPokemonItems.refresh() },
        indicator = { state, trigger ->
            PokeballRefreshIndicator(state = state, refreshTriggerDistance = trigger)
        }
    ) {
        LazyVerticalGrid(
            cells = GridCells.Adaptive(112.dp)
        ) {
            items(lazyPokemonItems.itemCount) { index ->
                lazyPokemonItems[index]?.let {
                    Pokemon(navController, it)
                }
            }
        }
    }

    lazyPokemonItems.apply {
            when {
                loadState.refresh is LoadState.Error -> {
                    val e = lazyPokemonItems.loadState.refresh as LoadState.Error
                    Toast.makeText(
                        LocalContext.current,
                        e.error.localizedMessage
                            ?: stringResource(R.string.error_pokemon_detail_default),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                loadState.append is LoadState.Error -> {
                    val e = lazyPokemonItems.loadState.append as LoadState.Error
                    Toast.makeText(
                        LocalContext.current,
                        e.error.localizedMessage
                            ?: stringResource(R.string.error_pokemon_detail_default),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
}

@ExperimentalFoundationApi
@ExperimentalPagingApi
@ExperimentalCoilApi
@Composable
fun Pokemon(navController: NavController, pokemon: PokemonFromList) {
    PokedexTheme {
        Column(
            modifier = Modifier
                .height(128.dp)
                .padding(4.dp)
                .clickable(
                    onClick = {
                        navController.navigate(
                            "${MainActivity.NAV_POKEMON_DETAIL}/${pokemon.id}"
                        )
                    }
                ),
            verticalArrangement = Arrangement.Bottom
        ) {
            Image(
                painter = rememberImagePainter(
                    data = pokemon.mainPictureUrl,
                    builder = {
                        crossfade(true)
                        placeholder(R.drawable.ic_pokemon_placeholder)
                        error(R.drawable.ic_pokemon_placeholder)
                        diskCachePolicy(CachePolicy.ENABLED)
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