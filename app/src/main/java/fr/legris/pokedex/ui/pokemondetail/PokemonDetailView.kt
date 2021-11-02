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
import fr.legris.pokedex.ui.model.Pokemon
import fr.legris.pokedex.utils.Resource

@Composable
fun PokemonDetailView(
    viewModel: PokemonDetailViewModel,
    pokemonId: Int
) {
    val scrollState = rememberScrollState()
    val pokemon by viewModel.pokemon.observeAsState()

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
                Text(text = "Détail LOADING $pokemonId")
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
                Text(text = "Détail SUCCESS $pokemonId")
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
                Text(text = "Détail ERROR $pokemonId")
            }
        }
    }

}