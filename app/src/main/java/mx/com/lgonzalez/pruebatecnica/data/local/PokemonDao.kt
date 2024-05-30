package mx.com.lgonzalez.pruebatecnica.data.local


import androidx.room.*


@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemonEntity: PokemonEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertType(typeEntity: TypeEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPokemonType(pokemonType: PokemonTypeEntity)

    @Query("SELECT * FROM pokemons LIMIT :limit OFFSET :offset")
    suspend fun getPokemons(limit: Int, offset: Int): List<PokemonWithTypes>

    @Query("SELECT * FROM pokemons WHERE name == :name")
    suspend fun getPokemonWithTypes(name: String): PokemonWithTypes

    @Query("SELECT * FROM pokemons WHERE name == :name")
    suspend fun getPokemonByName(name: String): PokemonEntity?

    @Query("SELECT * FROM types WHERE name == :name")
    suspend fun getType(name: String): TypeEntity?

    @Update
    suspend fun updatePokemon(pokemonEntity: PokemonEntity)
}