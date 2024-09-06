package com.iteris.counterapp.screens.policies

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iteris.counterapp.R
import com.iteris.counterapp.screens.policies.components.PolicyScreenSection
import com.iteris.counterapp.ui.theme.CounterAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PoliciesScreen(onPop: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "App Policies") },
                navigationIcon = {
                    IconButton(onClick = onPop) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Navigate Back"
                        )
                    }
                }
            )
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
    CounterAppTheme {
        PoliciesScreen(onPop = {})
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PrevDark() {
    CounterAppTheme {
        PoliciesScreen(onPop = {})
    }
}
