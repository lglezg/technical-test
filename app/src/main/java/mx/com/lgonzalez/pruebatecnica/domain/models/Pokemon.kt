package mx.com.lgonzalez.pruebatecnica.domain.models

import mx.com.lgonzalez.pruebatecnica.data.models.Sprites
import mx.com.lgonzalez.pruebatecnica.data.models.Type

data class Pokemon(
    val height: Int,
    val id: Int,
    val name: String,
    val urlImage: String,
    val types: List<String>,
    val weight: Int,
    val isFavorite: Boolean
)
