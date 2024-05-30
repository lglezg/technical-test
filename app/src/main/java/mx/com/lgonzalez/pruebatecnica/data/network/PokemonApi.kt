package mx.com.lgonzalez.pruebatecnica.data.network

import mx.com.lgonzalez.pruebatecnica.data.models.PokemonDetails
import mx.com.lgonzalez.pruebatecnica.data.models.PokemonList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET("pokemon")
    suspend fun getPokemons(@Query("limit") limit: Int, @Query("offset") offset: Int): Response<PokemonList>

    @GET("pokemon/{name}")
    suspend fun getPokemon(@Path("name") name: String): Response<PokemonDetails>
}