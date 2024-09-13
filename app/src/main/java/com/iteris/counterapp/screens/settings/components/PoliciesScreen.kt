package com.iteris.counterapp.screens.settings.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.iteris.counterapp.ui.components.listitems.IconListItem

@Composable
fun PoliciesListItem(onClick: () -> Unit) {
    IconListItem(
        modifier = Modifier.clickable(onClick = onClick),
        leadingIcon = Icons.Default.Policy,
        title = "Policies Page",
        description = "Read important information",
    )
}
