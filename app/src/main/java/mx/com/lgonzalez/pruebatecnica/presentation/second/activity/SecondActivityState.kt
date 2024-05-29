package mx.com.lgonzalez.pruebatecnica.presentation.second.activity

import mx.com.lgonzalez.pruebatecnica.domain.models.PokemonDetails

data class SecondActivityState(
    val isLoading : Boolean = false,
    val pokemons : List<PokemonDetails?> = emptyList()
)
