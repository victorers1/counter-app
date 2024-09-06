package com.iteris.counterapp.screens.settings.components

import androidx.compose.runtime.Composable
import com.iteris.counterapp.ui.components.dialogs.ConfirmDialog

@Composable
fun EraseAppDataConfirmDialog(
    onDismiss: () -> Unit,
    onConfim: () -> Unit
) {
    ConfirmDialog(
        title = "Are you sure?",
        description = "This will erase all your data",
        onDismiss = onDismiss,
        onConfirm = onConfim
    )
}
