package mx.com.lgonzalez.pruebatecnica.data.repositories

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import mx.com.lgonzalez.pruebatecnica.domain.models.LocationData
import mx.com.lgonzalez.pruebatecnica.domain.repositories.LocationRepository
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
    private val application: Application,
    private val firestore: FirebaseFirestore
) : LocationRepository{
    @SuppressLint("MissingPermission")
    override fun getCurrentLocation(): Flow<Location?> {
        return callbackFlow {
            val locationManager = application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val isGpsEnable  = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            val isNetworkEnable  = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
            if (!isGpsEnable && !isNetworkEnable){
                launch { send(null) }
            }

            val request = LocationRequest.create()
                .setInterval(120000)
                .setInterval(120000)

            val locationCallback = object : LocationCallback(){
                override fun onLocationResult(result: LocationResult) {
                    super.onLocationResult(result)
                    result.locations.lastOrNull()?.let {location ->
                        launch { send(location) }
                    }
                }
            }

            fusedLocationProviderClient.requestLocationUpdates(
                request,
                locationCallback,
                Looper.getMainLooper()
            )

            awaitClose{
                fusedLocationProviderClient.removeLocationUpdates(locationCallback)
            }
        }
    }

    override suspend fun saveLocation(locationData: LocationData) {
        firestore.collection("locations").add(locationData).await()
    }
}