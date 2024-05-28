package mx.com.lgonzalez.pruebatecnica.presentation.first.activity

import android.net.Uri

data class FirstActivityState (
    val text : String = "",
    val url: String = "",
    val initials: String = "",
    val placerHolder: Uri? = null

)