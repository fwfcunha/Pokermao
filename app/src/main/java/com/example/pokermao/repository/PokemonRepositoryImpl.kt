package com.example.pokermao.repository

import com.example.pokermao.api.PokemonService
import com.example.pokermao.model.HealthResponse
import com.example.pokermao.model.Pokemon
import com.example.pokermao.model.PokemonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonRepositoryImpl(var pokemonService: PokemonService) :
    PokemonRepository {

    override fun checkHealth(onComplete: () -> Unit, onError: (Throwable?) -> Unit) {
        pokemonService.checkHealth()
            .enqueue(object : Callback<HealthResponse> {
                override fun onFailure(call: Call<HealthResponse>, t: Throwable) {
                    onError(t)
                }
                override fun onResponse(call: Call<HealthResponse>, response:
                Response<HealthResponse>
                ) {
                    onComplete()
                }
            })
    }

    override fun getPokemons(size: Int, sort: String,onComplete: (List<Pokemon>?) -> Unit, onError: (Throwable?) -> Unit) {
        pokemonService.getPokemons(size, sort)
            .enqueue(object : Callback<PokemonResponse> {
                override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<PokemonResponse>,response:
                Response<PokemonResponse>
                ) {
                    if(response.isSuccessful){
                        onComplete(response.body()?.content ?: listOf())
                    }
                    else{
                        onError(Throwable("Nao foi possivel recarregar os pokemons"))
                    }
                }


            })

    }
}