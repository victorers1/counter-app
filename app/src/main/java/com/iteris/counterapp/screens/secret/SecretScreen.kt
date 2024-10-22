package com.iteris.counterapp.screens.secret

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iteris.counterapp.ui.components.topappbars.NavigateBackTopAppBar
import com.iteris.counterapp.ui.theme.PreviewAppTheme

@Composable
fun SecretScreen(onPop: () -> Unit) {
    Scaffold(topBar = { NavigateBackTopAppBar(title = "Back", onPop = onPop) }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp)
        ) {
            Text(
                "Don't tell anyone about this secret message! 🤫",
                modifier = Modifier.align(Alignment.Center),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PrevLight() {
    PreviewAppTheme {
        SecretScreen(onPop = {})
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PrevDark() {
    PreviewAppTheme {
        SecretScreen(onPop = {})
    }
}
