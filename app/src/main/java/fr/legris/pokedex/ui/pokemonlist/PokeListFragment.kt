package fr.legris.pokedex.ui.pokemonlist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import fr.legris.pokedex.R

@AndroidEntryPoint
class PokeListFragment : Fragment() {

    companion object {
        fun newInstance() = PokeListFragment()
    }

    private lateinit var viewModel: PokeListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.poke_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(PokeListViewModel::class.java)

        viewModel.pokemonList.observe(viewLifecycleOwner, {
            Log.d("POKEMON LIST", it.toString())
        })
    }

}