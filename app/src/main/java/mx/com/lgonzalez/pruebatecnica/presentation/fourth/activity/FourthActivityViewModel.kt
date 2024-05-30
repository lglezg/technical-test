package mx.com.lgonzalez.pruebatecnica.presentation.fourth.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mx.com.lgonzalez.pruebatecnica.domain.usecases.GetLocationsUseCase
import javax.inject.Inject

@HiltViewModel
class FourthActivityViewModel @Inject constructor(
    private val getLocationsUseCase: GetLocationsUseCase
) : ViewModel(){

    private val _state = MutableStateFlow(FourthActivityState())
    val state = _state.asStateFlow()

    init {
        getLocations()
    }

    private fun getLocations() = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }
        val locations = getLocationsUseCase.invoke()
        _state.update {
            it.copy(
                isLoading = false,
                markers = locations
            )
        }
    }

}