package mx.com.lgonzalez.pruebatecnica.domain.usecases

import mx.com.lgonzalez.pruebatecnica.domain.mappers.toEntity
import mx.com.lgonzalez.pruebatecnica.domain.models.Pokemon
import mx.com.lgonzalez.pruebatecnica.domain.repositories.PokemonRepository

class UpdatePokemonUseCaseImpl(
    private val pokemonRepository: PokemonRepository
) : UpdatePokemonUseCase{
    override suspend fun invoke(pokemon: Pokemon) {
       pokemonRepository.updatePokemon(pokemon.toEntity())
    }
}