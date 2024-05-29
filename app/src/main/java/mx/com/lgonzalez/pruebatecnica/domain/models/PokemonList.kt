package mx.com.lgonzalez.pruebatecnica.domain.models

import com.google.gson.annotations.SerializedName

data class PokemonList(
    @SerializedName("results") val results : List<PokemonItem>
)
