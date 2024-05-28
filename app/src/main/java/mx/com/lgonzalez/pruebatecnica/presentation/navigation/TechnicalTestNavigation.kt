package mx.com.lgonzalez.pruebatecnica.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mx.com.lgonzalez.pruebatecnica.TechnicalTestApp
import mx.com.lgonzalez.pruebatecnica.presentation.first.activity.FirstActivityScreen

@Composable
fun TechnicalTestNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.FirstActivity.route
    ){
        composable(Screens.FirstActivity.route){
            FirstActivityScreen(viewModel = hiltViewModel())
        }
    }
}