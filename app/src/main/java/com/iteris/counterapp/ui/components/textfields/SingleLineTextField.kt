package com.iteris.counterapp.ui.components.textfields

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iteris.counterapp.ui.theme.CounterAppTheme

@Composable
fun SingleLineTextField(
    initialValue: String = "", maxLength: Int, onChangedValue: (value: String) -> Unit
) {
    var text by remember { mutableStateOf(initialValue.take(maxLength)) }

    TextField(value = text,
        singleLine = true,
        label = { Text(text = "Counter name") },
        placeholder = { Text(text = "Counter") },
        onValueChange = {
            text = it.take(maxLength)
            onChangedValue(text)
        })
}

@Preview(showBackground = true)
@Composable
private fun SingleLineTextFieldPrevLight() {
    CounterAppTheme {
        Box(modifier = Modifier.padding(10.dp)) {
            SingleLineTextField(
                initialValue = "Counter Example", maxLength = 10,
                onChangedValue = {},
            )
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SingleLineTextFieldPrevDark() {
    CounterAppTheme {
        Box(modifier = Modifier.padding(10.dp)) {
            SingleLineTextField(
                initialValue = "Counter Example",
                maxLength = 12,
                onChangedValue = {},
            )
        }
    }
}
