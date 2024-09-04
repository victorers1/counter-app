package com.iteris.counterapp.screens.home.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iteris.counterapp.ui.theme.CounterAppTheme
import com.iteris.counterapp.ui.theme.Typography

@Composable
fun CounterButtonRow(
    modifier: Modifier = Modifier,
    value: Int,
    onDecrement: () -> Unit,
    onIncrement: () -> Unit
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        CounterButton(icon = Icons.Default.Remove, onCLick = onDecrement)

        Text(
            text = value.toString(),
            modifier = Modifier.padding(horizontal = 12.dp),
            style = Typography.titleLarge
        )

        CounterButton(icon = Icons.Default.Add, onCLick = onIncrement)

    }
}


@Preview(showBackground = true)
@Composable
private fun PrevLight() {
    CounterAppTheme {
        CounterButtonRow(value = 10, onDecrement = {}, onIncrement = {})
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PrevDark() {
    CounterAppTheme {
        CounterButtonRow(value = 10, onDecrement = {}, onIncrement = {})
    }
}
