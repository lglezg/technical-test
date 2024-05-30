package mx.com.lgonzalez.pruebatecnica.domain.usecases

import mx.com.lgonzalez.pruebatecnica.domain.models.LocationData
import mx.com.lgonzalez.pruebatecnica.domain.repositories.LocationRepository

class GetLocationsUseCaseImpl(
    private val  locationRepository: LocationRepository
) : GetLocationsUseCase {
    override suspend fun invoke(): List<LocationData> {
        return locationRepository.getLocations()
    }
}