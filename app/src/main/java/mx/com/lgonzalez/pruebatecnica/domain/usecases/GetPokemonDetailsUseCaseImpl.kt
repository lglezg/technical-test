package mx.com.lgonzalez.pruebatecnica.domain.usecases

import mx.com.lgonzalez.pruebatecnica.domain.models.PokemonDetails
import mx.com.lgonzalez.pruebatecnica.domain.repositories.PokemonRepository

class GetPokemonDetailsUseCaseImpl (
    private val pokemonRepository: PokemonRepository
    )
    : GetPokemonDetailsUseCase {
        override suspend fun invoke(name: String): PokemonDetails? {
            val networkResult = pokemonRepository.getPokemon(name)
            return networkResult.data
        }
    }