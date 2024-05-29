package mx.com.lgonzalez.pruebatecnica.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import mx.com.lgonzalez.pruebatecnica.ui.theme.LocalSpacing

@Composable
fun CustomColorPicker(
    isVisible: Boolean,
    onDismiss: ()-> Unit,
    onSelectedColor: (Color)->Unit
) {
    val controller = rememberColorPickerController()
    var selectedColor by remember { mutableStateOf(Color.White) }
    val localSpacing = LocalSpacing.current
    if (isVisible)
        Dialog(onDismissRequest = onDismiss) {
            Card {
                Column (
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    HsvColorPicker(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(450.dp)
                            .padding(10.dp),
                        controller = controller,
                        onColorChanged = { colorEnvelope: ColorEnvelope ->
                            selectedColor = colorEnvelope.color

                        }
                    )

                    Box(modifier = Modifier
                        .padding(top = localSpacing.spaceSmall)
                        .size(50.dp)
                        .background(selectedColor))

                    Button(onClick = {  onSelectedColor(selectedColor) }) {
                        Text(text = "OK")
                    }
                }
            }


        }
}