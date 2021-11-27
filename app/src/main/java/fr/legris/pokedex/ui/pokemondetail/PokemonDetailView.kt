package fr.legris.pokedex.ui.pokemondetail

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.request.CachePolicy
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import fr.legris.pokedex.R
import fr.legris.pokedex.ui.model.Pokemon
import fr.legris.pokedex.ui.model.Type
import fr.legris.pokedex.utils.Resource

@ExperimentalFoundationApi
@ExperimentalCoilApi
@Composable
fun PokemonDetailView(
    navController : NavHostController,
    viewModel: PokemonDetailViewModel = hiltViewModel()
) {
    val pokemon: Resource<Pokemon?>? by viewModel.pokemon.observeAsState()


    PokemonDetail(pokemon = pokemon?.data)
    IconButton(
        modifier = Modifier.padding(8.dp),
        onClick = {
            navController.popBackStack()
        }) {
        Icon(
            Icons.Filled.ArrowBack,
            "Bouton retour",
            tint = MaterialTheme.colors.onSurface)
    }

    when (pokemon?.status) {
        Resource.Status.LOADING -> {
        }
        Resource.Status.SUCCESS -> {
            if (pokemon?.data == null) {
                Toast.makeText(
                    LocalContext.current,
                    pokemon?.message
                        ?: stringResource(R.string.error_pokemon_detail_default),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        Resource.Status.ERROR -> {
            Toast.makeText(
                LocalContext.current,
                pokemon?.message
                    ?: stringResource(R.string.error_pokemon_detail_default),
                Toast.LENGTH_SHORT
            ).show()
        }

    }
}

@ExperimentalFoundationApi
@ExperimentalCoilApi
@Composable
fun PokemonDetail(pokemon: Pokemon?) {

    val scrollState = rememberScrollState()

    if (pokemon != null) {
        val animatedTopBarColor = animateColorAsState(
            targetValue = if (pokemon.types.isNotEmpty()) {
                pokemon.types[0].typeColor
            } else {
                MaterialTheme.colors.primaryVariant
            },
            animationSpec = tween(durationMillis = 300)
        )

        rememberSystemUiController()
            .setSystemBarsColor(color = animatedTopBarColor.value)

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
fun PokemonDetailName(pokemonName: String) {
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
fun PokemonDetailGlobalInfos(pokemon: Pokemon) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
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
fun PokemonDetailCharacteristics(pokemon: Pokemon) {
    Column(modifier = Modifier.fillMaxWidth()) {
        PokemonDetailCharacteristicItem(
            iconId = R.drawable.ic_height,
            text = stringResource(R.string.pokemon_detail_height, pokemon.height)
        )
        PokemonDetailCharacteristicItem(
            iconId = R.drawable.ic_weight,
            text = stringResource(R.string.pokemon_detail_weight, pokemon.weight)
        )
        PokemonDetailCharacteristicItem(
            iconId = R.drawable.ic_xp,
            text = stringResource(R.string.pokemon_detail_xp, pokemon.baseExperience)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row {
            pokemon.types.map {
                PokemonDetailTypeItem(type = it)
            }
        }
    }
}

@Composable
fun PokemonDetailCharacteristicItem(iconId: Int, text: String) {
    Row(
        modifier = Modifier.padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = painterResource(iconId), contentDescription = "")
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text)
    }
}

@Composable
fun PokemonDetailTypeItem(type: Type) {
    Box(
        modifier = Modifier
            .background(type.typeColor, shape = RoundedCornerShape(15))
            .border(1.dp, MaterialTheme.colors.onSurface, shape = RoundedCornerShape(15))
    ) {
        Text(
            text = type.typeName,
            modifier = Modifier.padding(4.dp)
        )
    }
    Spacer(modifier = Modifier.width(4.dp))
}


