package mx.com.lgonzalez.pruebatecnica.presentation.second.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mx.com.lgonzalez.pruebatecnica.domain.models.PokemonDetails
import mx.com.lgonzalez.pruebatecnica.domain.usecases.GetPokemonsUseCase
import javax.inject.Inject

@HiltViewModel
class SecondActivityViewModel @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase
): ViewModel(){

    private val _state = MutableStateFlow(SecondActivityState())
    val state = _state.asStateFlow()

    private var offset = 0

    init {
        getPokemons()
    }

    fun onEvent(event: SecondActivityEvent){
        when(event){
            is SecondActivityEvent.LoadMore -> loadMore()
        }
    }

    private fun loadMore() = viewModelScope.launch{
        offset += 25
        onIsLoadingChange(true)
        val newList = mutableListOf<PokemonDetails?>()
        val pokemonsState = _state.value.pokemons
        newList.addAll(pokemonsState)
        val pokemons = getPokemonsUseCase.invoke(25, offset = offset)
        newList.addAll(pokemons)
        _state.update { it.copy(isLoading = false, pokemons = newList) }
    }

    private fun getPokemons() = viewModelScope.launch {
        onIsLoadingChange(true)
        val pokemons = getPokemonsUseCase.invoke(25, offset = offset)
        _state.update { it.copy(isLoading = false, pokemons = pokemons) }
    }

    private fun onIsLoadingChange(isLoading: Boolean){
        _state.update { it.copy(isLoading = isLoading) }
    }
}