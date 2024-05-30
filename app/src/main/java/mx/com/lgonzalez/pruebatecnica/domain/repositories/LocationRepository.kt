package mx.com.lgonzalez.pruebatecnica.domain.repositories

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getCurrentLocation(): Flow<Location?>
}