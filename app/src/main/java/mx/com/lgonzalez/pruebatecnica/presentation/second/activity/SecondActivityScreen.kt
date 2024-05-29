package mx.com.lgonzalez.pruebatecnica.presentation.second.activity

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mx.com.lgonzalez.pruebatecnica.domain.models.PokemonDetails
import mx.com.lgonzalez.pruebatecnica.presentation.composables.CustomImage
import mx.com.lgonzalez.pruebatecnica.ui.theme.LocalSpacing

@Composable
fun SecondActivityScreen(
    viewModel: SecondActivityViewModel,
    navigateTo:  (String) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold {
        SecondActivityContent(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            state = state,
            navigateTo = navigateTo
        ) { event ->
            viewModel.onEvent(event)
        }
    }
}

@Composable
private fun SecondActivityContent(
    modifier: Modifier,
    state: SecondActivityState,
    navigateTo: (String) -> Unit,
    onEvent: (SecondActivityEvent) -> Unit
) {
    val localSpacing = LocalSpacing.current
    val listState = rememberLazyListState()

    val buffer = 1

    val reachedBottom: Boolean by remember {
        derivedStateOf {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem?.index != 0 && lastVisibleItem?.index == listState.layoutInfo.totalItemsCount - buffer
        }
    }

    LaunchedEffect(reachedBottom) {
        Log.e("PRB", "SecondActivityContent: $reachedBottom", )
        if (reachedBottom) onEvent(SecondActivityEvent.LoadMore)
    }
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(bottom = localSpacing.spaceLarge),
        state = listState
    ) {
        items(
            items = state.pokemons,
            key = { item: PokemonDetails? ->  item!!.id}
        ) { pokemonDetails ->
            pokemonDetails?.let {
                PokemonCard(
                    modifier = Modifier
                        .padding(all = localSpacing.spaceSmall)
                        .fillMaxWidth(),
                    pokemonDetails = it
                ) {name ->
                    navigateTo(name)
                }
            }
        }
        if (state.isLoading) {
            item {
                Box(
                    modifier = if (state.pokemons.isNotEmpty())
                        Modifier.fillMaxWidth()
                    else
                        Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }

    }
}

@Composable
fun PokemonCard(
    modifier: Modifier,
    pokemonDetails: PokemonDetails,
    onClick: (String) -> Unit
) {
    val localSpacing = LocalSpacing.current
    Card(
        modifier = modifier
            .height(100.dp)
            .clickable { onClick(pokemonDetails.name) },
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomImage(
                modifier = Modifier
                    .padding(end = localSpacing.spaceSmall)
                    .size(50.dp),
                url = pokemonDetails.sprites.frontDefault,
                initials = pokemonDetails.name,
                uri = null
            )
            Text(
                text = pokemonDetails.name,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }

}