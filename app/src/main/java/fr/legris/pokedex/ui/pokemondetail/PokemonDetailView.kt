package fr.legris.pokedex.ui.pokemondetail

import android.util.Log
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import fr.legris.pokedex.R
import fr.legris.pokedex.ui.model.Pokemon
import fr.legris.pokedex.utils.Resource
import kotlinx.coroutines.launch

@ExperimentalCoilApi
@Composable
fun PokemonDetailView(
    viewModel: PokemonDetailViewModel = hiltViewModel()
) {
    val pokemon: Resource<Pokemon?>? by viewModel.pokemon.observeAsState()

    val scrollState = rememberScrollState()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
    )
    {
        when (pokemon?.status) {
            Resource.Status.LOADING -> {
                LoadingScreen()
            }
            Resource.Status.SUCCESS -> {
                Log.d("RESOURCE SUCCESS", "RESOURCED" + pokemon?.data?.name ?: "No name")
                LoadingScreen()
            }
            Resource.Status.ERROR -> {
                Log.d("RESOURCE ERROR", "RESOURCED" + pokemon?.message ?: "Une erreur est survenue")
                ErrorSnackBar(
                    scaffoldState = scaffoldState,
                    message = pokemon?.message ?: "Une erreur est survenue"
                )
            }

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
            animation = tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing,
                delayMillis = 100
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    val rotate by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing,
                delayMillis = 200
            ),
            repeatMode = RepeatMode.Restart,
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    )
    {
        Image(
            painter = painterResource(id = R.drawable.ic_pokeball_loading),
            contentDescription = "Chargement",
            modifier = Modifier
                .fillMaxWidth(0.50f)
                .fillMaxHeight()
                .scale(scale)
                .rotate(rotate),
            contentScale = ContentScale.Inside
        )
    }
}

@Composable
fun ErrorSnackBar(scaffoldState: ScaffoldState, message: String) {
    LaunchedEffect(scaffoldState.snackbarHostState) {
        scaffoldState.snackbarHostState.showSnackbar(message).let {
            when (it) {
                SnackbarResult.Dismissed -> Log.d("TAG", "ScaffoldSnackbar: dismissed")
                SnackbarResult.ActionPerformed -> TODO()
            }
        }
    }
}

