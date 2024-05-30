package mx.com.lgonzalez.pruebatecnica.presentation.second.activity

import mx.com.lgonzalez.pruebatecnica.domain.models.Pokemon

sealed interface SecondActivityEvent {
    data object LoadMore : SecondActivityEvent

    data class FavoritePokemonChange(val pokemon: Pokemon): SecondActivityEvent
}