package mx.com.lgonzalez.pruebatecnica.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destination (val route: String, val icon: ImageVector, val label: String){
    data object FistActivity : Destination(Screens.FirstActivity.route, Icons.Default.AccountCircle, "First Activity")
    data object SecondActivity : Destination(Screens.SecondActivity.route, Icons.Default.Home, "Second Activity")
    data object FourthActivity : Destination(Screens.FourthActivity.route, Icons.Default.Place, "Fourth Activity")
}