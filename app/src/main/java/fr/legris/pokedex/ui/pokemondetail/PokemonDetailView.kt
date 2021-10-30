package fr.legris.pokedex.ui.pokemondetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.legris.pokedex.utils.Resource

@Composable
fun PokemonDetailView(viewModel: PokemonDetailViewModel, pokemonId : Int){
    val scrollState = rememberScrollState()
    val pokemon = viewModel.pokemon.observeAsState(initial = Resource.loading())
    viewModel.getPokemonById(pokemonId = pokemonId)

    Column (
        modifier = Modifier.verticalScroll(scrollState)
            .padding(8.dp)
            .fillMaxHeight()
            .fillMaxWidth()
            )
    {
        Text(text = "Détail $pokemonId")
    }

}