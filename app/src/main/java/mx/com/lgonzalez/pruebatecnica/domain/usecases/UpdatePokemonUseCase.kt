package mx.com.lgonzalez.pruebatecnica.domain.usecases

import mx.com.lgonzalez.pruebatecnica.domain.models.Pokemon

interface UpdatePokemonUseCase {
    suspend operator fun invoke(pokemon: Pokemon)
}