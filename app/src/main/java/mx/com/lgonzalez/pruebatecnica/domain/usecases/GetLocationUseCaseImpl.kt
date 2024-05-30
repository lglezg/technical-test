package mx.com.lgonzalez.pruebatecnica.domain.usecases

import android.location.Location
import kotlinx.coroutines.flow.Flow
import mx.com.lgonzalez.pruebatecnica.domain.repositories.LocationRepository
import javax.inject.Inject

class GetLocationUseCaseImpl @Inject constructor(
    private val locationRepository: LocationRepository
) : GetLocationUseCase{
    override fun invoke(): Flow<Location?> {
        return locationRepository.getCurrentLocation()
    }
}