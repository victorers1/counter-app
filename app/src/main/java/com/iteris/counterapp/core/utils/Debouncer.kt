package com.iteris.counterapp.core.utils

import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Debouncer(private val waitMs: Long = 300L) {

    private var job: Job? = null

    suspend fun run(function: suspend () -> Unit) {
        coroutineScope {
            job?.cancel()
            job = launch {
                delay(waitMs)
                function()
            }
        }
    }

    fun dispose() {
        job?.cancel()
        job = null
    }
}
