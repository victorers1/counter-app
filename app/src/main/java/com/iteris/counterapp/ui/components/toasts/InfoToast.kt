package com.iteris.counterapp.ui.components.toasts

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun showInfoToast(context: Context, text: String) {
    withContext(Dispatchers.Main) {
        Toast.makeText(
            context,
            text,
            Toast.LENGTH_LONG
        ).show()
    }
}
