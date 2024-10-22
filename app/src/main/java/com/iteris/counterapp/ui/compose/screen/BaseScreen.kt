package com.iteris.counterapp.ui.compose.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.iteris.counterapp.ui.compose.errors.ErrorState
import com.iteris.counterapp.ui.theme.DefaultAppTheme

/// Every screen has this composabled as Parent
@Composable
fun BaseScreen(
    loading: Boolean = false,
    error: ErrorState = ErrorState.NoError,
    onRetry: () -> Unit,
    onErrorClosed: () -> Unit,
    content: @Composable () -> Unit
) {
    DefaultAppTheme {
        Surface {
            Box(modifier = Modifier.fillMaxSize()) {
                content()
                if (loading) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFFF58220))
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(32.dp)
                        )
                    }
                } else if (error is ErrorState.CreationError) {
                    CreationErrorToast()
                } else if (error is ErrorState.UpdateError) {
                    UpdateErrorToast()
                } else if (error is ErrorState.DeletionError) {
                    DeletionErrorToast()
                } else if (error is ErrorState.ReadError) {
                    ReadErrorToast()
                }
            }
        }
    }
}
