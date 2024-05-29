package mx.com.lgonzalez.pruebatecnica.presentation.third.activity

import mx.com.lgonzalez.pruebatecnica.domain.models.Type

data class ThirdActivityState(
    val height: Int = 0,
    val id: Int = 0,
    val name: String = "",
    val urlImage: String = "",
    val types: List<Type> = emptyList(),
    val weight: Int = 0,
    val isLoading : Boolean = false
)
