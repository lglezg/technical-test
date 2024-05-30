package mx.com.lgonzalez.pruebatecnica.domain.repositories

import mx.com.lgonzalez.pruebatecnica.data.local.PokemonEntity
import mx.com.lgonzalez.pruebatecnica.data.models.PokemonDetails
import mx.com.lgonzalez.pruebatecnica.domain.models.Pokemon

interface PokemonRepository {
    suspend fun getPokemons(limit: Int, offset: Int): List<Pokemon>
    suspend fun getPokemonNetwork(name: String): PokemonDetails?
    suspend fun getPokemonLocal(name: String): Pokemon?
    suspend fun updatePokemon(pokemonEntity: PokemonEntity)
}