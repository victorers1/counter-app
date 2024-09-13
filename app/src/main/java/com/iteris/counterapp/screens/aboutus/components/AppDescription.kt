package com.iteris.counterapp.screens.aboutus.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.Link
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iteris.counterapp.R
import com.iteris.counterapp.ui.theme.PreviewAppTheme

@Composable
fun AppDescription(modifier: Modifier = Modifier) {
    val uriHandler = LocalUriHandler.current
    val personalLink = "https://victorbot.netlify.app/"

    val annotatedString = buildAnnotatedString {
        append(personalLink)
        addStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.primary,
                textDecoration = TextDecoration.Underline
            ),
            start = 0,
            end = personalLink.length
        )
        addStringAnnotation(
            tag = "URL",
            annotation = personalLink,
            start = 0,
            end = personalLink.length
        )
    }

    Column(modifier = modifier) {
        Text(
            text = "Counter App",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
        )

        Row(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.surfaceContainer,
                    shape = CircleShape
                )
                .padding(horizontal = 8.dp, vertical = 2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(12.dp),
                imageVector = Icons.Default.AlternateEmail, contentDescription = "Threads logo"
            )
            Spacer(modifier = Modifier.width(1.dp))
            Text(text = "victorers2", style = MaterialTheme.typography.bodySmall)
        }

        Text(
            text = "Developed with 💚 by Victor Emanuel using Jetpack Compose and Youtube",
            style = MaterialTheme.typography.bodyMedium
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier.padding(end = 2.dp),
                imageVector = Icons.Default.Link,
                contentDescription = "Personal link"
            )
            ClickableText(text = annotatedString,
                onClick = { uriHandler.openUri(personalLink) })
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PrevLight() {
    PreviewAppTheme {
        AppDescription()
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PrevDark() {
    PreviewAppTheme {
        AppDescription()
    }
}
