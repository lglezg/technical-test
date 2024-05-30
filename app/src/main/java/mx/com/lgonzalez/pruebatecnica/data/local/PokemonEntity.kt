package mx.com.lgonzalez.pruebatecnica.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemons")
data class PokemonEntity(
    @PrimaryKey val id: Int,
    val height: Int,
    val name: String,
    val urlImage: String,
    val weight: Int,
    @ColumnInfo(name = "is_favorite", defaultValue = "0") val isFavorite : Boolean = false
)
