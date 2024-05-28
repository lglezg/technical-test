package mx.com.lgonzalez.pruebatecnica.presentation.first.activity

import android.net.Uri

sealed interface FirstActivityEvent{
        data class OnUrlChange(val url: String): FirstActivityEvent
        data class OnTextChange(val text: String): FirstActivityEvent
        data class OnPlacerHolderChange(val uri: Uri): FirstActivityEvent
}