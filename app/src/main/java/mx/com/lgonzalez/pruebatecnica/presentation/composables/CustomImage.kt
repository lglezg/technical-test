package mx.com.lgonzalez.pruebatecnica.presentation.composables

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import mx.com.lgonzalez.pruebatecnica.R
import mx.com.lgonzalez.pruebatecnica.utils.extensions.initials

@Composable
fun CustomImage(
    modifier: Modifier,
    url: String,
    initials: String,
    uri: Uri?,
    textColor: Color = Color.Black,
    backgroundColor: Color = Color.LightGray
) {
    val modifierShape = modifier
        .border(
            border = BorderStroke(3.dp, textColor) ,
            shape = CircleShape
        )
        .clip(CircleShape)
        .background(backgroundColor)

    val contentScale =  ContentScale.Crop


    var isError by rememberSaveable { mutableStateOf(false) }
    if (url.isEmpty() || isError) {
        if (initials.isNotEmpty())
            Box(
                modifier = modifierShape,
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = initials.initials(),
                    style = MaterialTheme.typography.titleLarge,
                    color = textColor
                )
            }
        else
            Image(
                modifier = modifierShape,
                painter = if (uri != null)
                    rememberAsyncImagePainter(model = uri)
                else painterResource(id = R.drawable.image_placeholder),
                contentDescription = null,
                contentScale = contentScale
            )
    } else  {
        AsyncImage(
            modifier = modifierShape,
            model = url,
            contentDescription = null,
            contentScale = contentScale,
            onError = {
                isError = true
            },
            onLoading = {
                isError = false
            }
        )
    }


}