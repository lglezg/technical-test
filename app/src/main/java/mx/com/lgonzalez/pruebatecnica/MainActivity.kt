package mx.com.lgonzalez.pruebatecnica

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import dagger.hilt.android.AndroidEntryPoint
import mx.com.lgonzalez.pruebatecnica.framework.services.LocationService
import mx.com.lgonzalez.pruebatecnica.presentation.navigation.TechnicalTestNavigation
import mx.com.lgonzalez.pruebatecnica.ui.theme.PruebatecnicaTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val requestPermissionsLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ){ permissions ->
        val isGrantedFineLocation = permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false
        val isGrantedCoarseLocation = permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU){
            if (isGrantedCoarseLocation || isGrantedFineLocation){
                initService()
            }
        } else if  (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            val isGrantedPostNotifications = permissions[Manifest.permission.POST_NOTIFICATIONS] ?: false
            if ((isGrantedCoarseLocation || isGrantedFineLocation) && isGrantedPostNotifications){
                initService()
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkPermissions()

        setContent {
            PruebatecnicaTheme {
                TechnicalTestNavigation()
            }
        }
    }

    private fun checkPermissions(){

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU){
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED){
                initService()
            } else {
                requestPermissionsLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
        } else if  (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){

            if ((ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) && ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                == PackageManager.PERMISSION_GRANTED){
                initService()
            } else {
                requestPermissionsLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.POST_NOTIFICATIONS,
                    )
                )
            }
        }
    }

    private fun initService(){
        Intent(applicationContext, LocationService::class.java).apply {
            action = LocationService.ACTION_START
            startService(this)
        }
    }
}
