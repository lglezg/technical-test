package mx.com.lgonzalez.pruebatecnica.presentation.third.activity

import mx.com.lgonzalez.pruebatecnica.domain.models.Pokemon

sealed interface ThirdActivityEvent {
    data object FavoritePokemonChange: ThirdActivityEvent
}