package mx.com.lgonzalez.pruebatecnica.domain.usecases

import mx.com.lgonzalez.pruebatecnica.data.models.PokemonDetails
import mx.com.lgonzalez.pruebatecnica.domain.models.Pokemon
import mx.com.lgonzalez.pruebatecnica.domain.repositories.PokemonRepository

class GetPokemonDetailsUseCaseImpl(
    private val pokemonRepository: PokemonRepository
) : GetPokemonDetailsUseCase {
    override suspend fun invoke(name: String): Pokemon? {
        return pokemonRepository.getPokemonLocal(name)
    }
}