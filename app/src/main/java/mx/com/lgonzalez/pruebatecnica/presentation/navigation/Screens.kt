package mx.com.lgonzalez.pruebatecnica.presentation.navigation

sealed class Screens (val route: String){
    data object FirstActivity : Screens("first-activity")
}