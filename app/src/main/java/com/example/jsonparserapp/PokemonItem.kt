package com.example.jsonparserapp

data class PokemonItem(
    val id: Int,
    val name: Name
) {
    data class Name(
        val english: String,
        val french: String
    )
}


