package mx.com.lgonzalez.pruebatecnica.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.scopes.ServiceScoped
import mx.com.lgonzalez.pruebatecnica.domain.repositories.LocationRepository
import mx.com.lgonzalez.pruebatecnica.domain.usecases.GetLocationUseCase
import mx.com.lgonzalez.pruebatecnica.domain.usecases.GetLocationUseCaseImpl
import mx.com.lgonzalez.pruebatecnica.domain.usecases.SaveLocationUseCase
import mx.com.lgonzalez.pruebatecnica.domain.usecases.SaveLocationUseCaseImpl


@Module
@InstallIn(ServiceComponent::class)
object ServiceModule {
    @Provides
    @ServiceScoped
    fun provideGetLocationUseCase(
        locationRepository: LocationRepository
    ): GetLocationUseCase {
        return GetLocationUseCaseImpl(locationRepository)
    }
    @Provides
    @ServiceScoped
    fun provideSaveLocationUseCase(
        locationRepository: LocationRepository
    ): SaveLocationUseCase {
        return SaveLocationUseCaseImpl(locationRepository)
    }
}