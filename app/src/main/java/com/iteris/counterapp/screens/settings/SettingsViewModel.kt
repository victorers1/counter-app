package com.iteris.counterapp.screens.settings

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iteris.counterapp.domain.usecases.appstartinfo.DeleteAppStartupInfoUseCase
import com.iteris.counterapp.domain.usecases.counters.DeleteAllCountersUseCase
import com.iteris.counterapp.domain.usecases.settings.ReadAppSettingsUseCase
import com.iteris.counterapp.domain.usecases.settings.WriteAppSettingsParams
import com.iteris.counterapp.domain.usecases.settings.WriteAppSettingsUseCase
import com.iteris.counterapp.ui.compose.errors.ErrorState
import com.iteris.counterapp.ui.theme.ThemeModeEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val deleteAppStartupInfoUseCase: DeleteAppStartupInfoUseCase,
    private val deleteAllCountersUseCase: DeleteAllCountersUseCase,
    private val readAppSettingsUseCase: ReadAppSettingsUseCase,
    private val writeAppSettingsUseCase: WriteAppSettingsUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    init {
        loadSettings()
    }

    private fun loadSettings() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = readAppSettingsUseCase.execute()

            result.onSuccess {
                it.collect {
                    _uiState.update { state ->
                        state.copy(
                            themeModeEntity = it.themeMode, locale = it.locale
                        )
                    }
                }
            }.onFailure {
                _uiState.update { state -> state.copy(error = ErrorState.ReadError) }
            }
        }
    }

    fun onChangeThemeMode(newThemeModeEntity: ThemeModeEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            val params = WriteAppSettingsParams(
                newThemeModeEntity, _uiState.value.locale
            )
            val result = writeAppSettingsUseCase.execute(params)

            result.onSuccess {
                loadSettings()
            }.onFailure {
                _uiState.update { state -> state.copy(error = ErrorState.UpdateError) }
            }
        }
    }

    fun toggleEraseDataConfirmDialog() {
        _uiState.update {
            val isDialogVisible = it.showEraseDataConfirmDialog
            it.copy(showEraseDataConfirmDialog = !isDialogVisible)
        }
    }

    fun onEraseAllUserData() {
        var feedbackMsg: String

        viewModelScope.launch(Dispatchers.IO) {
            val deleteAllCountersResult = deleteAllCountersUseCase.execute()
            val deleteStartInfoResult = deleteAppStartupInfoUseCase.execute()

            val results = listOf(deleteAllCountersResult, deleteStartInfoResult)

            feedbackMsg = if (results.any { it.isFailure }) {
                "Some of the data couldn't be deleted due to an internal error. Please, try again."
            } else {
                "Your data has been deleted"
            }
            toggleEraseDataConfirmDialog()

            viewModelScope.launch(Dispatchers.Main) {
                Toast.makeText(context, feedbackMsg, Toast.LENGTH_LONG).show()
            }
        }
    }
}
