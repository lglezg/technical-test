package mx.com.lgonzalez.pruebatecnica.domain.repositories

import mx.com.lgonzalez.pruebatecnica.domain.models.PokemonDetails
import mx.com.lgonzalez.pruebatecnica.domain.models.PokemonList
import mx.com.lgonzalez.pruebatecnica.utils.network.NetworkResult

interface PokemonRepository {
    suspend fun getPokemons(limit: Int, offset: Int): NetworkResult<List<PokemonDetails?>>
    suspend fun getPokemon(name: String): NetworkResult<PokemonDetails>
}