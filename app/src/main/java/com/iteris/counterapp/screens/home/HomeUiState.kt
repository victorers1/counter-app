package com.iteris.counterapp.screens.home

import com.iteris.counterapp.domain.entities.AppStartupInfoEntity
import com.iteris.counterapp.domain.entities.counter.CounterEntity
import com.iteris.counterapp.ui.compose.errors.ErrorState

data class HomeUiState(
    val isLoading: Boolean = false,
    val isEditing: Boolean = false,
    val data: ArrayList<CounterEntity> = arrayListOf(),
    val counterNameMaxLength: Int = 30,
    val error: ErrorState = ErrorState.NoError,
    val errors: Map<String, String> = mutableMapOf(),
    val appStartupInfo: AppStartupInfoEntity? = null
)
