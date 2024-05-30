package mx.com.lgonzalez.pruebatecnica.domain.usecases

import mx.com.lgonzalez.pruebatecnica.domain.models.LocationData
import mx.com.lgonzalez.pruebatecnica.domain.repositories.LocationRepository

class SaveLocationUseCaseImpl(
    private val locationRepository: LocationRepository
) : SaveLocationUseCase{
    override suspend fun invoke(locationData: LocationData) {
        return locationRepository.saveLocation(locationData)
    }
}