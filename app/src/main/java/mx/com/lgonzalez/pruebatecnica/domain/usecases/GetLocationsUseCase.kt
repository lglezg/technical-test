package mx.com.lgonzalez.pruebatecnica.domain.usecases

import mx.com.lgonzalez.pruebatecnica.domain.models.LocationData

interface GetLocationsUseCase {
    suspend operator fun invoke(): List<LocationData>
}