package mx.com.lgonzalez.pruebatecnica.presentation.second.activity

sealed interface SecondActivityEvent {
    data object LoadMore : SecondActivityEvent
}