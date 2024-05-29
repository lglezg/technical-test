package mx.com.lgonzalez.pruebatecnica.presentation.navigation

sealed class Screens(val route: String) {
    data object FirstActivity : Screens("first-activity")
    data object SecondActivity : Screens("second-activity")
    data object ThirdActivity : Screens("third-activity")
    data object FourthActivity : Screens("fourth-activity")
}