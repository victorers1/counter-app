package com.iteris.counterapp.screens.settings.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.iteris.counterapp.BuildConfig

@Composable
fun AppVersion(modifier: Modifier = Modifier) {
    val versionName = BuildConfig.VERSION_NAME
    val versionCode = BuildConfig.VERSION_CODE

    Text(
        modifier = modifier,
        text = "v$versionName+$versionCode"
    )
}
