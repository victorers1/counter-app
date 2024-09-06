package com.iteris.counterapp.ui.components.listitems

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iteris.counterapp.ui.theme.CounterAppTheme

@Composable
fun IconListItem(
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector,
    title: String,
    description: String,
    trailingContent: @Composable () -> Unit
) {
    ListItem(
        modifier = modifier,
        leadingContent = {
            Icon(imageVector = leadingIcon, contentDescription = leadingIcon.toString())
        },
        headlineContent = { Text(text = title) },
        supportingContent = { Text(text = description) },
        trailingContent = trailingContent,
    )
}


@Preview
@Composable
private fun PrevLight() {
    CounterAppTheme {
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
    CounterAppTheme {
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
