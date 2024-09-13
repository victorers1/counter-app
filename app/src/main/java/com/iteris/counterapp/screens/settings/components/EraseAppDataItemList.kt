package com.iteris.counterapp.screens.settings.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.iteris.counterapp.ui.components.listitems.IconListItem

@Composable
fun EraseUserDataListItem(onCLick: ()-> Unit) {
    IconListItem(
        modifier = Modifier.clickable(onClick = onCLick),
        leadingIcon = Icons.Default.DeleteForever,
        title = "Delete data",
        description = "This can't be undone",
    )
}
