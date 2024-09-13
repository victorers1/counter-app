package com.iteris.counterapp.screens.aboutus

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iteris.counterapp.R
import com.iteris.counterapp.screens.aboutus.components.AppDescription
import com.iteris.counterapp.screens.aboutus.components.CallToActionButtonRow
import com.iteris.counterapp.screens.aboutus.components.SocialLinksRow
import com.iteris.counterapp.ui.components.avatars.CircularDrawableAvatar
import com.iteris.counterapp.ui.components.topappbars.NavigateBackTopAppBar
import com.iteris.counterapp.ui.compose.screen.BaseScreen
import com.iteris.counterapp.ui.theme.PreviewAppTheme

@Composable
fun AboutUsScreen(onPop: () -> Unit) {
    BaseScreen(onRetry = { /*TODO*/ }, onErrorClosed = { /*TODO*/ }) {
        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
            NavigateBackTopAppBar(
                title = "About us", onPop = onPop
            )
        }) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircularDrawableAvatar(
                        backgroundDrawableId = R.drawable.ic_launcher_background,
                        foregroundDrawableId = R.drawable.ic_launcher_foreground
                    )

                    SocialLinksRow(modifier = Modifier.padding(start = 24.dp, end = 16.dp))
                }

                Spacer(modifier = Modifier.height(8.dp))

                AppDescription(modifier = Modifier.padding(horizontal = 16.dp))

                Spacer(modifier = Modifier.height(8.dp))

                CallToActionButtonRow(modifier = Modifier.padding(horizontal = 16.dp))

            }
        }
    }
}

@Preview
@Composable
private fun PrevLight() {
    PreviewAppTheme {
        AboutUsScreen(onPop = {})
    }
}
