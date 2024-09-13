package com.iteris.counterapp.screens.policies

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iteris.counterapp.R
import com.iteris.counterapp.screens.policies.components.PolicyScreenSection
import com.iteris.counterapp.ui.components.topappbars.NavigateBackTopAppBar
import com.iteris.counterapp.ui.theme.PreviewAppTheme

@Composable
fun PoliciesScreen(onPop: () -> Unit) {
    Scaffold(
        topBar = {
            NavigateBackTopAppBar(title = "App Policies", onPop = onPop)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = innerPadding.calculateTopPadding())
                .verticalScroll(rememberScrollState()),
        ) {

            PolicyScreenSection(
                title = "Terms of Service",
                drawable = R.drawable.policies_deboa
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 64.dp))

            PolicyScreenSection(
                title = "Privacy Policy",
                drawable = R.drawable.policies_confia
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 64.dp))

            PolicyScreenSection(
                title = "Legal Representative",
                drawable = R.drawable.policies_desconfia
            )

            Box(modifier = Modifier.padding(vertical = innerPadding.calculateBottomPadding())) {}
        }
    }
}


@Preview
@Composable
private fun PrevLight() {
    PreviewAppTheme {
        PoliciesScreen(onPop = {})
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PrevDark() {
    PreviewAppTheme {
        PoliciesScreen(onPop = {})
    }
}
