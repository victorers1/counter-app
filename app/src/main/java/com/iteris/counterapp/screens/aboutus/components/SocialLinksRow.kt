package com.iteris.counterapp.screens.aboutus.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iteris.counterapp.domain.entities.SocialNetworkEntity
import com.iteris.counterapp.ui.theme.PreviewAppTheme

@Composable
fun SocialLinksRow(modifier: Modifier = Modifier) {
    val uriHandler = LocalUriHandler.current
    val networks = listOf(
        SocialNetworkEntity.LinkedIn,
        SocialNetworkEntity.GitHub,
        SocialNetworkEntity.Instagram
    )

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        networks.map { network ->
            Column(
                modifier = Modifier.clickable { uriHandler.openUri(network.profileUrl) },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = network.drawableId),
                    contentDescription = network.name
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = network.name, style = MaterialTheme.typography.labelMedium)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PrevLight() {
    PreviewAppTheme {
        SocialLinksRow()
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PrevDark() {
    PreviewAppTheme {
        SocialLinksRow()
    }
}
