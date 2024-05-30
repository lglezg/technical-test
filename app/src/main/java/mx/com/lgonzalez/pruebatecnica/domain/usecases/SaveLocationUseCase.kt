package mx.com.lgonzalez.pruebatecnica.domain.usecases

import mx.com.lgonzalez.pruebatecnica.domain.models.LocationData

interface SaveLocationUseCase {
    suspend operator fun invoke(locationData: LocationData)
}