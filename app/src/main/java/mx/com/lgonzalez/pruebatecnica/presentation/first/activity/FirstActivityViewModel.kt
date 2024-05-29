package mx.com.lgonzalez.pruebatecnica.presentation.first.activity

import android.net.Uri
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mx.com.lgonzalez.pruebatecnica.domain.usecases.GetInitialsUseCase
import javax.inject.Inject

@HiltViewModel
class FirstActivityViewModel @Inject constructor(
    private val getInitialsUseCase: GetInitialsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(FirstActivityState())
    val state = _state.asStateFlow()

    private var job: Job? = null

    fun onEvent(event: FirstActivityEvent) {
        when (event) {
            is FirstActivityEvent.OnUrlChange -> onUrlChange(event.url)
            is FirstActivityEvent.OnTextChange -> onTextChange(event.text)
            is FirstActivityEvent.OnPlacerHolderChange -> onPlaceHolderChange(event.uri)
            is FirstActivityEvent.OnTextColorPickerVisibleChange -> onTextColorPickerVisibleChange(event.isVisible)
            is FirstActivityEvent.OnBackgroundColorPickerVisibleChange -> onBackgroundColorPickerVisibleChange(event.isVisible)
            is FirstActivityEvent.OnBackgroundChange -> onBackGroundChange(event.color)
            is FirstActivityEvent.OnTextColorChange -> onTextColorChange(event.color)
        }
    }

    private fun onTextColorChange(color: Color) {
        _state.update { it.copy(textColor = color, isTextColorPickerVisible = false) }
    }

    private fun onBackGroundChange(color: Color) {
        _state.update { it.copy(backgroundColor = color, isBackgroundColorPickerVisible = false) }
    }

    private fun onTextColorPickerVisibleChange(isVisible: Boolean) {
        _state.update { it.copy(isTextColorPickerVisible = isVisible) }
    } private fun onBackgroundColorPickerVisibleChange(isVisible: Boolean) {
        _state.update { it.copy(isBackgroundColorPickerVisible = isVisible) }
    }

    private fun onUrlChange(url: String) {
        _state.update { it.copy(url = url) }
    }

    private fun onTextChange(text: String) {
        job?.cancel()
        _state.update { it.copy(text = text) }
        job = viewModelScope.launch {
            delay(2000)
            val initials = getInitialsUseCase.invoke(text)
            _state.update { it.copy(initials = initials) }
        }
    }

    private fun onPlaceHolderChange(uri: Uri) {
        _state.update { it.copy(placerHolder = uri) }
    }

}