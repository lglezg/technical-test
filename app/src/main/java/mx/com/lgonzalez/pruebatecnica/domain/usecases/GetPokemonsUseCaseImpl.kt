package mx.com.lgonzalez.pruebatecnica.domain.usecases

import mx.com.lgonzalez.pruebatecnica.domain.models.PokemonDetails
import mx.com.lgonzalez.pruebatecnica.domain.repositories.PokemonRepository

class GetPokemonsUseCaseImpl(
    private val pokemonRepository: PokemonRepository
)
    : GetPokemonsUseCase {
    override suspend fun invoke(limit: Int, offset: Int): List<PokemonDetails?> {
        val networkResult = pokemonRepository.getPokemons(limit, offset)
        return networkResult.data ?: emptyList<PokemonDetails>()
    }
}