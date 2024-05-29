package mx.com.lgonzalez.pruebatecnica.domain.models

data class PokemonDetails(
    val height: Int,
    val id: Int,
    val name: String,
    val sprites: Sprites,
    val types: List<Type>,
    val weight: Int
)