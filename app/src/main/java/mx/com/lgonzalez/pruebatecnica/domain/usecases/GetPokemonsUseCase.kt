package mx.com.lgonzalez.pruebatecnica.domain.usecases

import mx.com.lgonzalez.pruebatecnica.domain.models.Pokemon

interface GetPokemonsUseCase {

    suspend operator fun invoke(limit: Int, offset: Int): List<Pokemon>
}