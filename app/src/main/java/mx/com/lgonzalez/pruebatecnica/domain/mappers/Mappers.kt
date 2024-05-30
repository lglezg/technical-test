package mx.com.lgonzalez.pruebatecnica.domain.mappers

import mx.com.lgonzalez.pruebatecnica.data.local.PokemonEntity
import mx.com.lgonzalez.pruebatecnica.data.local.PokemonWithTypes
import mx.com.lgonzalez.pruebatecnica.domain.models.Pokemon

fun PokemonWithTypes.toDomainModel() : Pokemon {
    return Pokemon(
        height = this.pokemonEntity.height,
        id = this.pokemonEntity.id,
        name = this.pokemonEntity.name,
        urlImage = this.pokemonEntity.urlImage,
        types = this.types.map { it.name },
        weight = this.pokemonEntity.weight,
        isFavorite =  this.pokemonEntity.isFavorite,
    )
}

fun Pokemon.toEntity() : PokemonEntity{
    return PokemonEntity(
        height = this.height,
        id = this.id,
        name = this.name,
        urlImage = this.urlImage,
        weight = this.weight,
        isFavorite =  this.isFavorite,
    )
}