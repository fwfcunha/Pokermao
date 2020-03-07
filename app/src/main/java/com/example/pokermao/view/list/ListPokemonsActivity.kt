package com.example.pokermao.view.list


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.pokermao.R
import kotlinx.android.synthetic.main.include_loading.*
import org.koin.android.viewmodel.ext.android.viewModel

class ListPokemonsActivity : AppCompatActivity() {

    private val listPokemonsViewModel : ListPokemonsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_pokemons)

        listPokemonsViewModel.getPokemons()

        listPokemonsViewModel.isLoading.observe(this, Observer {
            //if(it) containerLoading.visibility = View.VISIBLE
            //else containerLoading.visibility = View.GONE

            containerLoading.visibility = if(it) View.VISIBLE else View.GONE
        })

        listPokemonsViewModel.messageError.observe(this, Observer {
            if(it != "") Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        listPokemonsViewModel.pokemons.observe(this, Observer {
            Toast.makeText(this, "Deu bom", Toast.LENGTH_LONG).show()
        })
    }
}
