package com.iteris.counterapp.screens.home.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.iteris.counterapp.ui.theme.PreviewAppTheme

@Composable
fun CounterButton(icon: ImageVector, onCLick: (() -> Unit)?) {
    IconButton(
        modifier = Modifier.background(
            color = MaterialTheme.colorScheme.primary,
            shape = CircleShape,
        ), colors = IconButtonDefaults.iconButtonColors(
            contentColor = MaterialTheme.colorScheme.onPrimary,
            containerColor = MaterialTheme.colorScheme.primary,
        ), onClick = onCLick ?: {}, enabled = onCLick != null
    ) {
        Icon(imageVector = icon, contentDescription = "Decrement counter value")
    }
}


@Preview(showBackground = true)
@Composable
private fun EnabledLight() {
    PreviewAppTheme {
        CounterButton(icon = Icons.Default.Home, onCLick = {})
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun EnabledDark() {
    PreviewAppTheme {
        CounterButton(icon = Icons.Default.Home, onCLick = {})
    }
}


@Preview(showBackground = true)
@Composable
private fun DisabledLight() {
    PreviewAppTheme {
        CounterButton(icon = Icons.Default.Home, onCLick = null)
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun DisabledDark() {
    PreviewAppTheme {
        CounterButton(icon = Icons.Default.Home, onCLick = null)
    }
}
