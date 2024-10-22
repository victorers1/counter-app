package com.iteris.counterapp.ui.components.listitems

import android.content.res.Configuration
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iteris.counterapp.ui.components.textfields.SingleLineTextField
import com.iteris.counterapp.ui.theme.DefaultAppTheme

@Composable
fun EditableListItem(
    isEditing: Boolean = false,
    headlineText: String,
    headLineMaxLength: Int,
    onDelete: () -> Unit,
    onChangedHeadline: (value: String) -> Unit,
    trailingContent: @Composable () -> Unit
) {
    ListItem(
        modifier = Modifier.animateContentSize(),
        headlineContent = {
            AnimatedContent(targetState = isEditing, label = "Headline content") { state ->
                when (state) {
                    true -> SingleLineTextField(
                        maxLength = headLineMaxLength,
                        initialValue = headlineText,
                        onChangedValue = onChangedHeadline,
                    )

                    else -> Text(
                        text = headlineText,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                    )
                }
            }
        },
        trailingContent = {
            AnimatedContent(targetState = isEditing, label = "Trailing content") { state ->
                when (state) {
                    true -> IconButton(onClick = onDelete) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete icon")
                    }

                    else -> trailingContent()
                }
            }
        },
    )
}


@Preview
@Composable
private fun PrevEditingLight() {
    DefaultAppTheme {
        EditableListItem(
            headlineText = "Counter name",
            onDelete = { },
            isEditing = true,
            headLineMaxLength = 10,
            onChangedHeadline = { },
            trailingContent = {
                Box(
                    Modifier
                        .background(MaterialTheme.colorScheme.secondary)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center,
                ) { Text(text = "Trailing") }
            },
        )
    }
}

@Preview
@Composable
private fun PrevNotEditingLight() {
    DefaultAppTheme {
        EditableListItem(
            headlineText = "Counter name",
            onDelete = { },
            isEditing = false,
            headLineMaxLength = 10,
            onChangedHeadline = { },
            trailingContent = {
                Box(
                    Modifier
                        .background(MaterialTheme.colorScheme.inversePrimary)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) { Text(text = "Trailing") }
            },
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PrevEditingDark() {
    DefaultAppTheme {
        EditableListItem(
            headlineText = "Counter name",
            onDelete = { },
            isEditing = true,
            headLineMaxLength = 10,
            onChangedHeadline = {},
            trailingContent = {
                Box(
                    Modifier
                        .background(MaterialTheme.colorScheme.secondary)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) { Text(text = "Trailing") }
            },
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PrevNotEditingDark() {
    DefaultAppTheme {
        EditableListItem(
            headlineText = "Counter name",
            onDelete = { },
            isEditing = false,
            headLineMaxLength = 10,
            onChangedHeadline = {},
            trailingContent = {
                Box(
                    Modifier
                        .background(MaterialTheme.colorScheme.inversePrimary)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) { Text(text = "Trailing") }
            },
        )
    }
}
