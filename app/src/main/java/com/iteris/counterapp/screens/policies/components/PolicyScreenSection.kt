package com.iteris.counterapp.screens.policies.components

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iteris.counterapp.R
import com.iteris.counterapp.ui.theme.PreviewAppTheme

@Composable
fun PolicyScreenSection(title: String, drawable: Int) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 24.dp),
        )
        Card(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Image(
                painter = painterResource(id = drawable),
                contentDescription = "A well detailed image containing the policy section",
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PrevLight() {
    PreviewAppTheme {
        PolicyScreenSection(title = "Terms of Usage", drawable = R.drawable.policies_confia)
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PrevDark() {
    PreviewAppTheme {
        PolicyScreenSection(title = "Terms of Usage", drawable = R.drawable.policies_confia)
    }
}
