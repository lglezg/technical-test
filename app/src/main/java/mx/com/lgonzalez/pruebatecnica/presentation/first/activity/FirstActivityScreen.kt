package mx.com.lgonzalez.pruebatecnica.presentation.first.activity

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mx.com.lgonzalez.pruebatecnica.presentation.composables.CustomColorPicker
import mx.com.lgonzalez.pruebatecnica.presentation.composables.CustomImage
import mx.com.lgonzalez.pruebatecnica.ui.theme.LocalSpacing

@Composable
fun FirstActivityScreen(
    viewModel: FirstActivityViewModel
) {


    val state by viewModel.state.collectAsStateWithLifecycle()

    CustomColorPicker(
        isVisible = state.isTextColorPickerVisible,
        onDismiss = {
            viewModel.onEvent(FirstActivityEvent.OnTextColorPickerVisibleChange(false))
        }
    ) {
        viewModel.onEvent(FirstActivityEvent.OnTextColorChange(it))
    }

    CustomColorPicker(
        isVisible = state.isBackgroundColorPickerVisible,
        onDismiss = {
            viewModel.onEvent(FirstActivityEvent.OnBackgroundColorPickerVisibleChange(false))
        }
    ) {
        viewModel.onEvent(FirstActivityEvent.OnBackgroundChange(it))
    }

    Scaffold {
        FirstActivityContent(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            state = state
        ) { event ->
            viewModel.onEvent(event)
        }
    }

}

@Composable
fun FirstActivityContent(
    modifier: Modifier,
    state: FirstActivityState,
    onEvent: (FirstActivityEvent) -> Unit
) {

    val context = LocalContext.current

    val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        Manifest.permission.READ_MEDIA_IMAGES
    else
        Manifest.permission.READ_EXTERNAL_STORAGE


    val launcherGallery =
        rememberLauncherForActivityResult(
            contract =
            ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            uri?.let { onEvent(FirstActivityEvent.OnPlacerHolderChange(uri)) }
        }

    val permissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {
            launcherGallery.launch("image/*")
        }

    val localSpacing = LocalSpacing.current




    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = localSpacing.spaceLarge),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CustomImage(
                modifier = Modifier
                    .padding(bottom = localSpacing.spaceSmall)
                    .size(200.dp),
                url = state.url,
                initials = state.initials,
                uri = state.placerHolder,
                textColor = state.textColor,
                backgroundColor = state.backgroundColor
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.url,
                onValueChange = {
                    onEvent(FirstActivityEvent.OnUrlChange(it))
                },
                label = {
                    Text(text = "Agrega un url para tu imagen")
                },
                placeholder = {
                    Text(
                        text = "Por ejemplo: https://www.ejemplo.com/img.jpg",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                maxLines = 2
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.text,
                onValueChange = {
                    onEvent(FirstActivityEvent.OnTextChange(it))
                },
                label = {
                    Text(text = "Ingresa tu nombre")
                },
                placeholder = {
                    Text(
                        text = "Leonardo Gonzalez",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            )

            Button(
                onClick = {
                    if (ContextCompat.checkSelfPermission(
                            context,
                            permission
                        ) == PackageManager.PERMISSION_GRANTED
                    )
                        launcherGallery.launch("image/*")
                    else
                        permissionLauncher.launch(permission)


                }
            ) {
                Text(text = "Agregar placeholder")
            }
            Button(
                onClick = {
                    onEvent(FirstActivityEvent.OnTextColorPickerVisibleChange(true))
                }
            ) {
                Text(text = "Cambiar color de texto")
            }
            Button(
                onClick = {
                    onEvent(FirstActivityEvent.OnBackgroundColorPickerVisibleChange(true))
                }
            ) {
                Text(text = "Cambiar color de fondo")
            }


        }

    }
}