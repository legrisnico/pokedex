package fr.legris.pokedex.ui.pokemondetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import fr.legris.pokedex.ui.model.Pokemon
import fr.legris.pokedex.utils.Resource

@Composable
fun PokemonDetailView(
    viewModel: PokemonDetailViewModel = hiltViewModel()
) {
    val pokemon by viewModel.pokemon.observeAsState()

    PokemonDetail(pokemon = pokemon)
}

@Composable
fun PokemonDetail(pokemon : Resource<Pokemon>?){
    val scrollState = rememberScrollState()
    when(pokemon?.status){
        Resource.Status.LOADING -> {
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .padding(8.dp)
                    .fillMaxHeight()
                    .fillMaxWidth()
            )
            {
                Text(text = "Détail LOADING")
            }
        }
        Resource.Status.SUCCESS -> {
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .padding(8.dp)
                    .fillMaxHeight()
                    .fillMaxWidth()
            )
            {
                Text(text = "Détail SUCCESS ${pokemon.data?.name}")
            }
        }
        Resource.Status.ERROR -> {
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .padding(8.dp)
                    .fillMaxHeight()
                    .fillMaxWidth()
            )
            {
                Text(text = "Détail ERROR ${pokemon.message}")
            }
        }
    }
}