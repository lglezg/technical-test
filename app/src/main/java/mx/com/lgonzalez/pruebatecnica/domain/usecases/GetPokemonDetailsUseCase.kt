package mx.com.lgonzalez.pruebatecnica.domain.usecases

import mx.com.lgonzalez.pruebatecnica.domain.models.PokemonDetails

interface GetPokemonDetailsUseCase {
    suspend operator fun invoke(name: String): PokemonDetails?
}