package com.iteris.counterapp.ui.compose.errors


sealed class ErrorState {
    data object NoError : ErrorState()
    data object ReadError : ErrorState()
    data object CreationError : ErrorState()
    data object UpdateError : ErrorState()
    data object DeletionError : ErrorState()
}
