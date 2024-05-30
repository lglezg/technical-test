package mx.com.lgonzalez.pruebatecnica.domain.repositories

import android.location.Location
import kotlinx.coroutines.flow.Flow
import mx.com.lgonzalez.pruebatecnica.domain.models.LocationData

interface LocationRepository {
    fun getCurrentLocation(): Flow<Location?>
    suspend fun saveLocation(locationData: LocationData)
    suspend fun getLocations():List<LocationData>
}