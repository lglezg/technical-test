package mx.com.lgonzalez.pruebatecnica.presentation.first.activity

import android.net.Uri
import androidx.compose.ui.graphics.Color

data class FirstActivityState (
    val text : String = "",
    val url: String = "",
    val initials: String = "",
    val placerHolder: Uri? = null,
    val isTextColorPickerVisible : Boolean = false,
    val isBackgroundColorPickerVisible : Boolean = false,
    val textColor : Color = Color.Black,
    val backgroundColor : Color = Color.LightGray
)