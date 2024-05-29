package mx.com.lgonzalez.pruebatecnica.domain.usecases

import mx.com.lgonzalez.pruebatecnica.domain.models.PokemonDetails

interface GetPokemonsUseCase {

    suspend operator fun invoke(limit: Int, offset: Int): List<PokemonDetails?>
}