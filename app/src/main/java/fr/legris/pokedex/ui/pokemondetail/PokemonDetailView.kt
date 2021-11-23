package fr.legris.pokedex.ui.pokemondetail

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyGridScope
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import fr.legris.pokedex.R
import fr.legris.pokedex.ui.model.Pokemon
import fr.legris.pokedex.ui.model.Type
import fr.legris.pokedex.ui.pokemonlist.Pokemon
import fr.legris.pokedex.ui.theme.PokedexTheme
import fr.legris.pokedex.ui.theme.electricTypeColorDark
import fr.legris.pokedex.utils.Resource

@ExperimentalFoundationApi
@ExperimentalCoilApi
@Composable
fun PokemonDetailView(
    viewModel: PokemonDetailViewModel = hiltViewModel()
) {
    val pokemon: Resource<Pokemon?>? by viewModel.pokemon.observeAsState()

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
    )
    {
        when (pokemon?.status) {
            Resource.Status.LOADING -> {

            }
            Resource.Status.SUCCESS -> {
                if(pokemon?.data == null){
                    ErrorSnackBar(
                        scaffoldState = scaffoldState,
                        message = pokemon?.message ?: stringResource(R.string.error_pokemon_detail_default)
                    )
                }
                PokemonDetail(pokemon = pokemon?.data!!)
            }
            Resource.Status.ERROR -> {
                ErrorSnackBar(
                    scaffoldState = scaffoldState,
                    message = pokemon?.message ?: stringResource(R.string.error_pokemon_detail_default)
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
        scaffoldState.snackbarHostState.showSnackbar(message)
    }
}

@ExperimentalFoundationApi
@ExperimentalCoilApi
@Composable
fun PokemonDetail(pokemon: Pokemon){

    val scrollState = rememberScrollState()

    val animatedBackgroundColor = animateColorAsState(
        targetValue = when {
            pokemon.types.isEmpty() -> {
                MaterialTheme.colors.surface
            }
            isSystemInDarkTheme() -> {
                pokemon.types[0].typeColorDark
            }
            else -> {
                pokemon.types[0].typeColorLight
            }
        },
        animationSpec = tween(durationMillis = 200)
    )

    if(pokemon.types.isNotEmpty()) {
        rememberSystemUiController()
            .setSystemBarsColor(color = pokemon.types[0].typeColor)
    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(animatedBackgroundColor.value)) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            PokemonDetailName(pokemonName = pokemon.name)
            PokemonDetailGlobalInfos(pokemon = pokemon)
        }
    }

}

@Composable
fun PokemonDetailName(pokemonName : String){
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        textAlign = TextAlign.Center,
        text = pokemonName,
        fontSize = 18.sp
    )
}

@ExperimentalFoundationApi
@ExperimentalCoilApi
@Composable
fun PokemonDetailGlobalInfos(pokemon: Pokemon){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
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
                .fillMaxWidth(0.50f)
                .height(128.dp)
        )
        PokemonDetailCharacteristics(pokemon = pokemon)
    }
}

@ExperimentalFoundationApi
@Composable
fun PokemonDetailCharacteristics(pokemon : Pokemon){
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = pokemon.height.toString())
        Text(text = pokemon.weight.toString())
        Text(text = pokemon.baseExperience.toString())
        pokemon.types.map {
            PokemonDetailTypeItem(type = it)
        }
    }
}

@Composable
fun PokemonDetailTypeItem(type : Type){
    Text(text = type.typeName)
}

