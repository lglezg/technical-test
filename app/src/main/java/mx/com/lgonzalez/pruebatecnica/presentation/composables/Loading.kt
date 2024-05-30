package mx.com.lgonzalez.pruebatecnica.presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import mx.com.lgonzalez.pruebatecnica.ui.theme.PruebatecnicaTheme

@Composable
fun Loading(
    modifier: Modifier
) {
    Box (
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyLoadingPreview(){
    PruebatecnicaTheme {
        Loading(modifier = Modifier.fillMaxSize())
    }
}