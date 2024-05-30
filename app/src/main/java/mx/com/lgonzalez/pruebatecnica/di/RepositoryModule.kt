package mx.com.lgonzalez.pruebatecnica.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.com.lgonzalez.pruebatecnica.data.repositories.LocationRepositoryImpl
import mx.com.lgonzalez.pruebatecnica.data.repositories.PokemonRepositoryImpl
import mx.com.lgonzalez.pruebatecnica.domain.repositories.LocationRepository
import mx.com.lgonzalez.pruebatecnica.domain.repositories.PokemonRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindPokemonRepository(pokemonRepositoryImpl: PokemonRepositoryImpl): PokemonRepository

    @Binds
    abstract fun bindLocationRepository(locationRepositoryImpl: LocationRepositoryImpl): LocationRepository
}