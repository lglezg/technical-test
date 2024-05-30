package mx.com.lgonzalez.pruebatecnica.presentation.fourth.activity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import mx.com.lgonzalez.pruebatecnica.domain.models.LocationData
import mx.com.lgonzalez.pruebatecnica.presentation.composables.EmptyData
import mx.com.lgonzalez.pruebatecnica.presentation.composables.Loading
import mx.com.lgonzalez.pruebatecnica.ui.theme.LocalSpacing
import mx.com.lgonzalez.pruebatecnica.ui.theme.PruebatecnicaTheme

@Composable
fun FourthActivityScreen(
    viewModel: FourthActivityViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold {
        if (state.isLoading)
            Loading(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            )
        else
            FourthActivityContent(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                state = state
            )
    }
}

@Composable
fun FourthActivityContent(
    modifier: Modifier,
    state: FourthActivityState
) {

    val localSpacing = LocalSpacing.current

    if (state.markers.isEmpty())
        EmptyData(modifier = modifier)
    else
        ConstraintLayout(modifier) {
            val firstLocation = state.markers.first()
            val cameraLatLng = LatLng(firstLocation.latitude, firstLocation.longitude)
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(cameraLatLng, 10f)
            }
            val (map, locations) = createRefs()

            GoogleMap(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .constrainAs(map) {
                        top.linkTo(parent.top)
                    },
                cameraPositionState = cameraPositionState
            ) {
                state.markers.forEach {
                    val latLng = LatLng(it.latitude, it.longitude)

                    Marker(
                        state = MarkerState(latLng),
                        title = it.dateTime
                    )
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(locations) {
                        top.linkTo(map.bottom)
                        bottom.linkTo(parent.bottom)
                        height = Dimension.fillToConstraints
                    },
                contentPadding = PaddingValues(
                    horizontal = localSpacing.spaceMedium,
                    vertical = localSpacing.spaceLarge
                )
            ) {
                items(state.markers) { location ->
                    LocationDetails(
                        modifier = Modifier.fillMaxWidth(),
                        locationData = location
                    )
                }
            }

        }

}


@Composable
fun LocationDetails(
    modifier: Modifier,
    locationData: LocationData
) {
    Column(modifier) {
        Text(text = "Hora: ${locationData.dateTime}")
        Text(text = "Ubicación: ${locationData.latitude} , ${locationData.longitude}")
        HorizontalDivider(modifier = Modifier.fillMaxWidth())
    }
}

@Preview(showBackground = true)
@Composable
fun LocationDetailsPreview() {
    PruebatecnicaTheme {
        LocationDetails(
            modifier = Modifier.fillMaxWidth(),
            locationData = LocationData(0.0, 0.0, "-")
        )
    }
}