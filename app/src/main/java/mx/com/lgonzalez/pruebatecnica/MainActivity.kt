package mx.com.lgonzalez.pruebatecnica

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import mx.com.lgonzalez.pruebatecnica.presentation.navigation.TechnicalTestNavigation
import mx.com.lgonzalez.pruebatecnica.ui.theme.PruebatecnicaTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PruebatecnicaTheme {
                TechnicalTestNavigation()
            }
        }
    }
}
