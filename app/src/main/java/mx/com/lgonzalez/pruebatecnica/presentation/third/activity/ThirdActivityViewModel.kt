package mx.com.lgonzalez.pruebatecnica.presentation.third.activity

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mx.com.lgonzalez.pruebatecnica.domain.usecases.GetPokemonDetailsUseCase
import javax.inject.Inject

@HiltViewModel
class ThirdActivityViewModel @Inject  constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase
): ViewModel(){

    private val _state = MutableStateFlow(ThirdActivityState())
    val state = _state.asStateFlow()

    init {
        getPokemonDetails()
    }

    private fun getPokemonDetails() = viewModelScope.launch {
        val pokemonName = checkNotNull(savedStateHandle["name"]) as String
        onIsLoadingChange(true)
        val pokemonDetails = getPokemonDetailsUseCase.invoke(pokemonName)
        pokemonDetails?.let {
            _state.update {
                it.copy(
                    height = pokemonDetails.height,
                    id = pokemonDetails.id,
                    name = pokemonDetails.name,
                    urlImage = pokemonDetails.sprites.frontDefault,
                    types = pokemonDetails.types,
                    weight = pokemonDetails.weight
                )
            }
        }
        onIsLoadingChange(false)

    }

    private fun onIsLoadingChange(isLoading: Boolean){
        _state.update { it.copy(isLoading = isLoading) }
    }


}