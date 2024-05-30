package mx.com.lgonzalez.pruebatecnica.presentation.third.activity

import mx.com.lgonzalez.pruebatecnica.data.models.Type

data class ThirdActivityState(
    val height: Int = 0,
    val id: Int = 0,
    val name: String = "",
    val urlImage: String = "",
    val types: List<String> = emptyList(),
    val weight: Int = 0,
    val isFavorite: Boolean = false,
    val isLoading : Boolean = false
)
