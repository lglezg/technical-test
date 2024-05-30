package mx.com.lgonzalez.pruebatecnica.data.local

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation


data class PokemonWithTypes (
    @Embedded val pokemonEntity: PokemonEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(PokemonTypeEntity::class, parentColumn = "pokemon_id", entityColumn = "type_id")
    )
    val types: List<TypeEntity>
)