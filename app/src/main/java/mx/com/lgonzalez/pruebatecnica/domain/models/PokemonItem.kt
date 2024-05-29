package mx.com.lgonzalez.pruebatecnica.domain.models

import com.google.gson.annotations.SerializedName

data class PokemonItem(
    @SerializedName("name") val name: String
)
