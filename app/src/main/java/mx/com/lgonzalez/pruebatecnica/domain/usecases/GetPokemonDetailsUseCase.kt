package mx.com.lgonzalez.pruebatecnica.domain.usecases

import mx.com.lgonzalez.pruebatecnica.domain.models.Pokemon

interface GetPokemonDetailsUseCase {
    suspend operator fun invoke(name: String): Pokemon?
}