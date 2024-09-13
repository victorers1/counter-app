package com.iteris.counterapp.screens.home.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iteris.counterapp.ui.theme.PreviewAppTheme

@Composable
fun CounterButtonRow(
    modifier: Modifier = Modifier, value: Int, onDecrement: () -> Unit, onIncrement: () -> Unit
) {

    var oldValue by remember { mutableIntStateOf(value) }
    SideEffect { oldValue = value }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        CounterButton(icon = Icons.Default.Remove, onCLick = onDecrement)
        Spacer(modifier = Modifier.padding(6.dp))

        AnimatedCounter(value = value, oldValue = oldValue)

        Spacer(modifier = Modifier.padding(6.dp))
        CounterButton(icon = Icons.Default.Add, onCLick = onIncrement)
    }
}


@Preview(showBackground = true)
@Composable
private fun PrevLight() {
    PreviewAppTheme {
        CounterButtonRow(value = 10, onDecrement = {}, onIncrement = {})
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PrevDark() {
    PreviewAppTheme {
        CounterButtonRow(value = 10, onDecrement = {}, onIncrement = {})
    }
}
