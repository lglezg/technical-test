package mx.com.lgonzalez.pruebatecnica.data.repositories

import android.app.Application
import android.util.Log
import mx.com.lgonzalez.pruebatecnica.data.local.PokemonEntity
import mx.com.lgonzalez.pruebatecnica.data.local.PokemonTypeEntity
import mx.com.lgonzalez.pruebatecnica.data.local.TechnicalTestDatabase
import mx.com.lgonzalez.pruebatecnica.data.local.TypeEntity
import mx.com.lgonzalez.pruebatecnica.data.models.PokemonDetails
import mx.com.lgonzalez.pruebatecnica.data.network.PokemonApi
import mx.com.lgonzalez.pruebatecnica.domain.mappers.toDomainModel
import mx.com.lgonzalez.pruebatecnica.domain.models.Pokemon
import mx.com.lgonzalez.pruebatecnica.domain.repositories.PokemonRepository
import mx.com.lgonzalez.pruebatecnica.utils.network.NetworkConnection
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonApi: PokemonApi,
    private val technicalTestDatabase: TechnicalTestDatabase,
    private val application: Application
) : PokemonRepository {
    override suspend fun getPokemons(
        limit: Int,
        offset: Int
    ): List<Pokemon> {
        if (NetworkConnection.isOnline(application))
            syncronizePokemons(limit, offset)
        return technicalTestDatabase.pokemonDao().getPokemons(limit, offset)
            .map { pokemonWithTypes ->
                pokemonWithTypes.toDomainModel()
            }

    }

    override suspend fun getPokemonNetwork(name: String): PokemonDetails? {
        return try {
            val response = pokemonApi.getPokemon(name)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getPokemonLocal(name: String): Pokemon {
        return technicalTestDatabase.pokemonDao().getPokemonWithTypes(name).toDomainModel()
    }

    override suspend fun updatePokemon(pokemonEntity: PokemonEntity) {
        technicalTestDatabase.pokemonDao().updatePokemon(pokemonEntity)
    }

    private suspend fun syncronizePokemons(limit: Int, offset: Int) {
        try {
            val response = pokemonApi.getPokemons(limit, offset)
            if (response.isSuccessful) {
                val body = response.body()
                val result = body?.results?.map { pokemonItem ->
                    getPokemonNetwork(pokemonItem.name)
                } ?: emptyList()

                result.forEach { pokemonDetails ->
                    if (pokemonDetails != null) {
                        insertPokemon(pokemonDetails)
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("Pokemon", "syncronizePokemons: ${e.localizedMessage}")
        }
    }

    private suspend fun insertPokemon(pokemonDetails: PokemonDetails) {
        val pokemonEntity = technicalTestDatabase.pokemonDao().getPokemonByName(pokemonDetails.name)
        if (pokemonEntity == null) {
            technicalTestDatabase.pokemonDao().insertPokemon(
                PokemonEntity(
                    id = pokemonDetails.id,
                    height = pokemonDetails.height,
                    name = pokemonDetails.name,
                    urlImage = pokemonDetails.sprites.frontDefault,
                    weight = pokemonDetails.weight,
                )
            )
            pokemonDetails.types.forEach { type ->
                val typeEntity = technicalTestDatabase.pokemonDao().getType(type.type.name)
                val typeId = typeEntity?.id
                    ?: technicalTestDatabase.pokemonDao().insertType(
                        TypeEntity(name = type.type.name)
                    )

                technicalTestDatabase.pokemonDao().insertPokemonType(
                    PokemonTypeEntity(
                        pokemonId = pokemonDetails.id,
                        typeId = typeId.toInt()
                    )
                )
            }
        }
    }


}