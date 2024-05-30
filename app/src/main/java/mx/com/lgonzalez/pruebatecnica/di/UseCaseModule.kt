package mx.com.lgonzalez.pruebatecnica.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import mx.com.lgonzalez.pruebatecnica.domain.repositories.PokemonRepository
import mx.com.lgonzalez.pruebatecnica.domain.usecases.GetPokemonDetailsUseCase
import mx.com.lgonzalez.pruebatecnica.domain.usecases.GetPokemonDetailsUseCaseImpl
import mx.com.lgonzalez.pruebatecnica.domain.usecases.GetPokemonsUseCase
import mx.com.lgonzalez.pruebatecnica.domain.usecases.GetPokemonsUseCaseImpl
import mx.com.lgonzalez.pruebatecnica.domain.usecases.UpdatePokemonUseCase
import mx.com.lgonzalez.pruebatecnica.domain.usecases.UpdatePokemonUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {


    @Provides
    @ViewModelScoped
    fun provideGetPokemonsUseCase(
        pokemonRepository: PokemonRepository
    ): GetPokemonsUseCase {
        return GetPokemonsUseCaseImpl(pokemonRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetPokemonDetailsUseCase(
        pokemonRepository: PokemonRepository
    ): GetPokemonDetailsUseCase {
        return GetPokemonDetailsUseCaseImpl(pokemonRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideUpdatePokemonUseCase(
        pokemonRepository: PokemonRepository
    ): UpdatePokemonUseCase {
        return UpdatePokemonUseCaseImpl(pokemonRepository)
    }
}