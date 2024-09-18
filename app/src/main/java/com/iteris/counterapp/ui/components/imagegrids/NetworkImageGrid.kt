package com.iteris.counterapp.ui.components.imagegrids

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.iteris.counterapp.R
import com.iteris.counterapp.ui.theme.PreviewAppTheme
import kotlin.math.ceil

@Composable
fun NetworkImageGrid(urls: List<String>, nColumns: Int = 3) {
    val spaceBetweenItems = 2.dp

    val screenWidth = LocalConfiguration.current.screenWidthDp

    val horizontalWhiteSpace = spaceBetweenItems.times(nColumns - 1)

    val itemHeight = screenWidth.div(nColumns).minus(horizontalWhiteSpace.value).dp

    val numberOfRows = ceil(urls.size / nColumns.toDouble()).toInt()

    val verticalWhiteSpace = spaceBetweenItems.times(numberOfRows - 1)

    val gridHeight = itemHeight.times(numberOfRows).plus(verticalWhiteSpace)

    LazyVerticalGrid(
        columns = GridCells.Fixed(nColumns),
        modifier = Modifier.height(gridHeight),
        userScrollEnabled = false,
        verticalArrangement = Arrangement.spacedBy(spaceBetweenItems),
        horizontalArrangement = Arrangement.spacedBy(spaceBetweenItems)
    ) {

        items(urls.size) { index ->
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(urls[index])
                    .crossfade(true)
                    .build(),
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.surfaceTint)
                    .height(itemHeight),
                contentDescription = "Image from network",
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.policies_confia),
                placeholder = painterResource(id = R.drawable.ic_launcher_background)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PrevLight() {
    PreviewAppTheme {
        NetworkImageGrid(urls = List(10) { "https://avatar.iran.liara.run/public" })
    }
}
