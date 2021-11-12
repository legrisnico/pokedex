package fr.legris.pokedex.ui.pokemondetail

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import fr.legris.pokedex.R
import fr.legris.pokedex.ui.model.Pokemon
import fr.legris.pokedex.utils.Resource

@ExperimentalCoilApi
@Composable
fun PokemonDetailView(
    viewModel: PokemonDetailViewModel = hiltViewModel()
) {
    val pokemon: Resource<Pokemon>? by viewModel.pokemon.observeAsState()

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(8.dp)
            .fillMaxHeight()
            .fillMaxWidth()
    )
    {

    }
    when (pokemon?.status) {
        Resource.Status.LOADING -> {
            LoadingScreen()
        }
        Resource.Status.SUCCESS -> {
            LoadingScreen()
        }
        Resource.Status.ERROR -> {
            LoadingScreen()
        }
    }
}

@ExperimentalCoilApi
@Composable
fun LoadingScreen() {
    val infiniteTransition = rememberInfiniteTransition()

    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(500),
            repeatMode = RepeatMode.Reverse
        )
    )

    val rotate by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Restart,
        )
    )

    Image(
        alignment = Alignment.Center,
        painter = painterResource(id = R.drawable.ic_loader_pokemon_dark),
        contentDescription = "Chargement",
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .scale(scale)
            .rotate(rotate),
        contentScale = ContentScale.Inside
    )
}

