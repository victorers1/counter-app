package com.iteris.counterapp.screens.settings.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.iteris.counterapp.ui.components.listitems.IconListItem

@Composable
fun EraseUserDataListItem(onClick: () -> Unit) {
    IconListItem(
        modifier = Modifier.clickable(onClick = onClick),
        leadingIcon = Icons.Default.DeleteForever,
        title = "Delete data",
        description = "This can't be undone",
    )
}
