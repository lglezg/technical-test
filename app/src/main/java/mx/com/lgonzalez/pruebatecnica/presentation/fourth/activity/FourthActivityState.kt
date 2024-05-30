package mx.com.lgonzalez.pruebatecnica.presentation.fourth.activity

import com.google.type.LatLng
import mx.com.lgonzalez.pruebatecnica.domain.models.LocationData

data class FourthActivityState(
    val markers : List<LocationData> = emptyList(),
    val isLoading : Boolean = false
)
