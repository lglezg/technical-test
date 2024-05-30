package mx.com.lgonzalez.pruebatecnica.presentation.third.activity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mx.com.lgonzalez.pruebatecnica.R
import mx.com.lgonzalez.pruebatecnica.presentation.composables.CustomImage
import mx.com.lgonzalez.pruebatecnica.presentation.second.activity.SecondActivityEvent
import mx.com.lgonzalez.pruebatecnica.ui.theme.LocalSpacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThirdActivityScreen(
    viewModel: ThirdActivityViewModel,
    onPopBackStack: () ->Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = { onPopBackStack() }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back),
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text(
                        text = state.name,
                        style = MaterialTheme.typography.displayMedium
                    )
                }
            )
        }
    ) {
        ThirdActivityContent(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            state = state
        ){ event ->
            viewModel.onEvent(event)
        }
    }
}

@Composable
fun ThirdActivityContent(
    modifier: Modifier,
    state: ThirdActivityState,
    onEvent: (ThirdActivityEvent) -> Unit
) {

    val localSpacing = LocalSpacing.current

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        Card(
            modifier = Modifier
                .padding(horizontal = localSpacing.spaceLarge)
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(localSpacing.spaceMedium),
                verticalArrangement = Arrangement.Center,

                ) {

                IconButton(
                    modifier = Modifier.align(Alignment.End),
                    onClick = { onEvent(ThirdActivityEvent.FavoritePokemonChange) }
                ) {
                    Icon(
                        painter = painterResource(
                            id = if (state.isFavorite)
                                R.drawable.baseline_favorite
                            else
                                R.drawable.baseline_favorite_border
                        ),
                        contentDescription = null,
                        tint = Color.Red
                    )
                }
                CustomImage(
                    modifier = Modifier
                        .size(150.dp)
                        .align(Alignment.CenterHorizontally),
                    url = state.urlImage,
                    initials = state.name,
                    uri = null
                )

                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    text = state.name,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(text = "Peso: ${state.weight}")
                Text(text = "Altura: ${state.height}")
                Text(
                    text = "Tipos",
                    style = MaterialTheme.typography.titleLarge
                )
                state.types.forEach {type ->
                    Text(text = type)
                }
            }
        }
    }
}