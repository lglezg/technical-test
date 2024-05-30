package mx.com.lgonzalez.pruebatecnica.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        PokemonEntity::class,
        TypeEntity::class,
        PokemonTypeEntity::class,
    ],
    version = 1,
)
abstract class TechnicalTestDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}