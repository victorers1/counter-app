package com.iteris.counterapp.screens.aboutus.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iteris.counterapp.ui.theme.PreviewAppTheme

@Composable
fun ElevatedButtonRow(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth().height(32.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ElevatedRoundedButton(
            modifier = Modifier.weight(1f),
            label = "Follow", onClick = { /*TODO*/ }
        )
        Spacer(modifier = Modifier.padding(4.dp))
        ElevatedRoundedButton(
            modifier = Modifier.weight(1f),
            label = "Contact", onClick = { /*TODO*/ }
        )
        Spacer(modifier = Modifier.padding(4.dp))
        ElevatedRoundedButton(
            modifier = Modifier.weight(1f),
            label = "Message", onClick = { /*TODO*/ }
        )
    }
}

@Composable
fun ElevatedRoundedButton(modifier: Modifier = Modifier, label: String, onClick: () -> Unit) {
    ElevatedButton(
        modifier = modifier,
        onClick = onClick,
        contentPadding = PaddingValues(0.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PrevLight() {
    PreviewAppTheme {
        ElevatedButtonRow()
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PrevDark() {
    PreviewAppTheme {
        ElevatedButtonRow()
    }
}
