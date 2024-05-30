package mx.com.lgonzalez.pruebatecnica.presentation.third.activity

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mx.com.lgonzalez.pruebatecnica.domain.models.Pokemon
import mx.com.lgonzalez.pruebatecnica.domain.usecases.GetPokemonDetailsUseCase
import mx.com.lgonzalez.pruebatecnica.domain.usecases.UpdatePokemonUseCase
import javax.inject.Inject

@HiltViewModel
class ThirdActivityViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase,
    private val updatePokemonUseCase: UpdatePokemonUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(ThirdActivityState())
    val state = _state.asStateFlow()

    private lateinit var pokemon: Pokemon

    init {
        getPokemonDetails()
    }

    fun onEvent(event: ThirdActivityEvent) {
        when (event) {
            is ThirdActivityEvent.FavoritePokemonChange -> favoritePokemonChange()
        }
    }

    private fun favoritePokemonChange() = viewModelScope.launch {
        updatePokemonUseCase.invoke(pokemon.copy(isFavorite = !pokemon.isFavorite))
        val isFavorite = _state.value.isFavorite
        _state.update { it.copy(isFavorite = !isFavorite) }
    }

    private fun getPokemonDetails() = viewModelScope.launch {
        val pokemonName = checkNotNull(savedStateHandle["name"]) as String
        onIsLoadingChange(true)
        val pokemonDetails = getPokemonDetailsUseCase.invoke(pokemonName)

        pokemonDetails?.let {
            pokemon = pokemonDetails
            _state.update {
                it.copy(
                    height = pokemonDetails.height,
                    id = pokemonDetails.id,
                    name = pokemonDetails.name,
                    urlImage = pokemonDetails.urlImage,
                    types = pokemonDetails.types,
                    weight = pokemonDetails.weight,
                    isFavorite = pokemonDetails.isFavorite,
                )
            }
        }
        onIsLoadingChange(false)

    }

    private fun onIsLoadingChange(isLoading: Boolean) {
        _state.update { it.copy(isLoading = isLoading) }
    }


}