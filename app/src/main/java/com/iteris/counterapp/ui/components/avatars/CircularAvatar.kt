package com.iteris.counterapp.ui.components.avatars

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iteris.counterapp.R
import com.iteris.counterapp.ui.theme.PreviewAppTheme

@Composable
fun CircularDrawableAvatar(backgroundDrawableId: Int, foregroundDrawableId: Int) {
    val avatarSize = 72.dp;

    Box(
        modifier = Modifier
            .size(avatarSize)
            .shadow(elevation = 5.dp, shape = CircleShape)
            .clip(CircleShape)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = backgroundDrawableId),
            contentDescription = "Circular avatar background",
        )
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = foregroundDrawableId),
            contentScale = ContentScale.Fit,
            contentDescription = "Circular avatar foreground"
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun PrevLight() {
    PreviewAppTheme {
        Box(modifier = Modifier.padding(8.dp)) {
            CircularDrawableAvatar(
                backgroundDrawableId = R.drawable.ic_launcher_background,
                foregroundDrawableId = R.drawable.ic_launcher_foreground
            )
        }
    }
}
