package com.example.pokermao.repository

import com.example.pokermao.model.Pokemon

interface PokemonRepository{
    fun checkHealth(
        onComplete:() -> Unit,
        onError:(Throwable?) -> Unit
    )
    fun getPokemons(
        size: Int,
        sort: String,
        onComplete: (List<Pokemon>?) -> Unit,
        onError: (Throwable?) -> Unit
    )
}