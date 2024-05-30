package mx.com.lgonzalez.pruebatecnica.domain.usecases

import mx.com.lgonzalez.pruebatecnica.domain.models.Pokemon
import mx.com.lgonzalez.pruebatecnica.domain.repositories.PokemonRepository

class GetPokemonsUseCaseImpl(
    private val pokemonRepository: PokemonRepository
)
    : GetPokemonsUseCase {
    override suspend fun invoke(limit: Int, offset: Int): List<Pokemon> {
        return pokemonRepository.getPokemons(limit, offset)
    }
}