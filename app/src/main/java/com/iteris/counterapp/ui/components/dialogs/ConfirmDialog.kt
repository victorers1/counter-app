package com.iteris.counterapp.ui.components.dialogs

import android.content.res.Configuration
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.iteris.counterapp.ui.theme.DefaultAppTheme

@Composable
fun ConfirmDialog(
    title: String, description: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        title = { Text(text = title) },
        text = { Text(text = description) },
        onDismissRequest = onDismiss,
        dismissButton = { DialogCancelButton(text = "Cancel", onCLick = onDismiss) },
        confirmButton = { DialogConfirmButton(text = "Confirm", onCLick = onConfirm) },
    )
}


@Composable
fun DialogConfirmButton(text: String, onCLick: () -> Unit) {
    Button(onClick = onCLick) {
        Text(text = text)
    }
}

@Composable
fun DialogCancelButton(text: String, onCLick: () -> Unit) {
    TextButton(
        colors = ButtonDefaults.textButtonColors(
            contentColor = MaterialTheme.colorScheme.error,
        ),
        onClick = onCLick
    ) {
        Text(text = text)
    }
}


@Preview
@Composable
private fun PrevLight() {
    DefaultAppTheme {
        ConfirmDialog(
            title = "Are you sure?",
            description = "Are you sure?",
            onConfirm = { }, onDismiss = {}
        )
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PrevDark() {
    DefaultAppTheme {
        ConfirmDialog(
            title = "Are you sure?",
            description = "Are you sure?",
            onConfirm = { }, onDismiss = {}
        )
    }
}
