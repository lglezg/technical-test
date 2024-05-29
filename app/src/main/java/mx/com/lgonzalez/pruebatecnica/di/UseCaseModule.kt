package mx.com.lgonzalez.pruebatecnica.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import mx.com.lgonzalez.pruebatecnica.domain.repositories.PokemonRepository
import mx.com.lgonzalez.pruebatecnica.domain.usecases.GetInitialsUseCase
import mx.com.lgonzalez.pruebatecnica.domain.usecases.GetInitialsUseCaseImpl
import mx.com.lgonzalez.pruebatecnica.domain.usecases.GetPokemonDetailsUseCase
import mx.com.lgonzalez.pruebatecnica.domain.usecases.GetPokemonDetailsUseCaseImpl
import mx.com.lgonzalez.pruebatecnica.domain.usecases.GetPokemonsUseCase
import mx.com.lgonzalez.pruebatecnica.domain.usecases.GetPokemonsUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideGetInitials(): GetInitialsUseCase {
        return GetInitialsUseCaseImpl()
    }

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
}