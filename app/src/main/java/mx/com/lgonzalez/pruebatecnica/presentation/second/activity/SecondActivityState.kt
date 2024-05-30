package mx.com.lgonzalez.pruebatecnica.presentation.second.activity

import mx.com.lgonzalez.pruebatecnica.data.models.PokemonDetails
import mx.com.lgonzalez.pruebatecnica.domain.models.Pokemon

data class SecondActivityState(
    val isLoading : Boolean = false,
    val pokemons : List<Pokemon> = emptyList()
)
