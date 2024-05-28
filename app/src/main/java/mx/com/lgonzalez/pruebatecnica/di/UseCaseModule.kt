package mx.com.lgonzalez.pruebatecnica.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import mx.com.lgonzalez.pruebatecnica.domain.usecases.GetInitialsUseCase
import mx.com.lgonzalez.pruebatecnica.domain.usecases.GetInitialsUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideGetInitials(): GetInitialsUseCase {
        return GetInitialsUseCaseImpl()
    }
}