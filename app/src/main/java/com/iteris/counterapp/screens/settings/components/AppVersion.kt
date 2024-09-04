package com.iteris.counterapp.screens.settings.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.iteris.counterapp.BuildConfig

@Composable
fun AppVersion() {
    val versionName = BuildConfig.VERSION_NAME
    val versionCode = BuildConfig.VERSION_CODE

    Text(text = "v$versionName+$versionCode")
}
