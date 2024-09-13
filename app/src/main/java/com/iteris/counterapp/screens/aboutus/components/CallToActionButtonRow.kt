package com.iteris.counterapp.screens.aboutus.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CallToActionButtonRow(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        ElevatedButton(onClick = { /*TODO*/ }) {
            Text(text = "Follow")
        }
        ElevatedButton(onClick = { /*TODO*/ }) {
            Text(text = "Contact")
        }
        ElevatedButton(onClick = { /*TODO*/ }) {
            Text(text = "Follow")
        }
    }
}
