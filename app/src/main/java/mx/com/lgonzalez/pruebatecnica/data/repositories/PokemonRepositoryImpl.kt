package mx.com.lgonzalez.pruebatecnica.data.repositories

import mx.com.lgonzalez.pruebatecnica.domain.models.PokemonDetails
import mx.com.lgonzalez.pruebatecnica.domain.models.PokemonList
import mx.com.lgonzalez.pruebatecnica.data.network.PokemonApi
import mx.com.lgonzalez.pruebatecnica.domain.repositories.PokemonRepository
import mx.com.lgonzalez.pruebatecnica.utils.network.NetworkResult
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonApi: PokemonApi
) : PokemonRepository{
    override suspend fun getPokemons(limit: Int, offset: Int): NetworkResult<List<PokemonDetails?>> {
        return try {
            val response = pokemonApi.getPokemons(limit, offset)
            if (response.isSuccessful){
                val body = response.body()
                val result = body?.results?.map { pokemonItem ->
                   getPokemon(pokemonItem.name).data
                }

                NetworkResult.Success(result)
            } else{
                NetworkResult.Error(response.message())
            }
        } catch (e: Exception){
            NetworkResult.Error(e.message ?: "Error")
        }
    }

    override suspend fun getPokemon(name: String): NetworkResult<PokemonDetails> {
        return try {
            val response = pokemonApi.getPokemon(name)
            if (response.isSuccessful){
                NetworkResult.Success(response.body())
            } else{
                NetworkResult.Error(response.message())
            }
        } catch (e: Exception){
            NetworkResult.Error(e.message ?: "Error")
        }
    }



}