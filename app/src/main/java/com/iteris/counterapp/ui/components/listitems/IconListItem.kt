package com.iteris.counterapp.ui.components.listitems

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iteris.counterapp.ui.theme.DefaultAppTheme

@Composable
fun IconListItem(
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector?,
    title: String,
    description: String,
    trailingContent: @Composable (() -> Unit)? = null
) {
    val fonsScale = LocalDensity.current.fontScale
    val fontScaleThreshold = 1.3

    Column {
        ListItem(
            modifier = modifier,
            leadingContent = {
                if (leadingIcon != null) {
                    Icon(imageVector = leadingIcon, contentDescription = leadingIcon.toString())
                }
            },
            headlineContent = { Text(text = title) },
            supportingContent = {
                if (fonsScale <= fontScaleThreshold && description.isNotBlank()) Text(
                    text = description
                )
            },
            trailingContent = {
                trailingContent?.invoke() ?: Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowRight,
                    contentDescription = "Forward arrow"
                )
            }
        )

        if (fonsScale > fontScaleThreshold) HorizontalDivider()
    }
}

@Preview
@Composable
private fun PrevLight() {
    DefaultAppTheme {
        IconListItem(
            leadingIcon = Icons.Default.Settings,
            title = "Settings",
            description = "Change app settings"
        ) {
            Box(
                Modifier
                    .background(MaterialTheme.colorScheme.onSurface)
                    .padding(16.dp)
            ) {
                Text(text = "Placeholder")
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PrevDark() {
    DefaultAppTheme {
        IconListItem(
            leadingIcon = Icons.Default.Settings,
            title = "Settings",
            description = "Change app settings"
        ) {
            Box(
                Modifier
                    .background(MaterialTheme.colorScheme.onSurface)
                    .padding(16.dp)
            ) {
                Text(text = "Placeholder")
            }
        }
    }
}
