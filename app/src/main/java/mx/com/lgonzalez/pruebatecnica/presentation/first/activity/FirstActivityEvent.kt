package mx.com.lgonzalez.pruebatecnica.presentation.first.activity

import android.net.Uri
import androidx.compose.ui.graphics.Color

sealed interface FirstActivityEvent{
        data class OnUrlChange(val url: String): FirstActivityEvent
        data class OnTextChange(val text: String): FirstActivityEvent
        data class OnPlacerHolderChange(val uri: Uri): FirstActivityEvent
        data class OnTextColorPickerVisibleChange(val isVisible: Boolean): FirstActivityEvent
        data class OnBackgroundColorPickerVisibleChange(val isVisible: Boolean): FirstActivityEvent
        data class OnTextColorChange(val color: Color): FirstActivityEvent
        data class OnBackgroundChange(val color: Color): FirstActivityEvent
}