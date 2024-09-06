package com.iteris.counterapp.screens.home

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iteris.counterapp.core.extensions.toDateAndHour
import com.iteris.counterapp.core.utils.Debouncer
import com.iteris.counterapp.domain.entities.CounterEntity
import com.iteris.counterapp.domain.usecases.counters.CreateCounterParams
import com.iteris.counterapp.domain.usecases.counters.CreateCounterUseCase
import com.iteris.counterapp.domain.usecases.counters.DeleteCounterParams
import com.iteris.counterapp.domain.usecases.counters.DeleteCounterUseCase
import com.iteris.counterapp.domain.usecases.counters.ReadAllCountersUseCase
import com.iteris.counterapp.domain.usecases.appstartinfo.ReadAppStartupInfoUseCase
import com.iteris.counterapp.domain.usecases.counters.UpdateCounterParams
import com.iteris.counterapp.domain.usecases.counters.UpdateCounterUseCase
import com.iteris.counterapp.domain.usecases.appstartinfo.WriteAppStartupInfoParams
import com.iteris.counterapp.domain.usecases.appstartinfo.WriteAppStartupInfoUseCase
import com.iteris.counterapp.ui.compose.errors.ErrorState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val createCounterUseCase: CreateCounterUseCase,
    private val readAllCountersUseCase: ReadAllCountersUseCase,
    private val updateCounterUseCase: UpdateCounterUseCase,
    private val deleteCounterUseCase: DeleteCounterUseCase,
    private val readAppStartupInfoUseCase: ReadAppStartupInfoUseCase,
    private val writeAppStartupInfoUseCase: WriteAppStartupInfoUseCase,
    @ApplicationContext private val context: Context,
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    private val _debouncer = Debouncer()

    init {
        loadAppStartupInfo(context)
    }

    fun loadAllCounters() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = readAllCountersUseCase.execute()
            result.onSuccess { counters ->
                _uiState.update { state -> state.copy(data = counters) }
            }.onFailure {
                _uiState.update { state -> state.copy(error = ErrorState.ReadError) }
            }
        }
    }

    private fun loadAppStartupInfo(context: Context) {
        viewModelScope.launch {

            readAppStartupParams()

            val dateLastOpened = _uiState.value.appStartupInfo?.date?.toDateAndHour()
            Toast.makeText(
                context,
                "Last time you've opened this app was $dateLastOpened",
                Toast.LENGTH_LONG
            ).show()

            val writeParams = WriteAppStartupInfoParams(
                count = (_uiState.value.appStartupInfo?.count ?: 0) + 1,
                date = Date()
            )
            val resultWrite = writeAppStartupInfoUseCase.execute(writeParams)

            resultWrite.onSuccess {
                readAppStartupParams()
                Toast.makeText(
                    context,
                    "You opened this app ${_uiState.value.appStartupInfo?.count} times",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private suspend fun readAppStartupParams() {
        val resultRead = readAppStartupInfoUseCase.execute()
        resultRead.onSuccess { startupInfo ->
            _uiState.update { state -> state.copy(appStartupInfo = startupInfo) }
        }
    }

    fun onStartEdit() {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(isEditing = !state.isEditing)
            }
        }
    }

    fun onDoneEdit() {
        _uiState.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value.data.forEach {
                val params = UpdateCounterParams(it.id, it.label, it.value)
                updateCounterUseCase.execute(params)
            }

            val result = readAllCountersUseCase.execute()

            result.onSuccess {
                _uiState.update { state ->
                    state.copy(
                        isLoading = false, isEditing = false, data = it
                    )
                }
            }.onFailure {
                _uiState.update {
                    it.copy(
                        isLoading = false, isEditing = false, error = ErrorState.ReadError
                    )
                }
            }
        }
    }

    fun onEditedHeadline(id: String, headline: String) {
        _uiState.update { state ->
            val newData = _uiState.value.data.map { counter ->
                if (counter.id == id) counter.copy(label = headline) else counter
            }.toCollection(ArrayList())
            state.copy(data = newData)
        }
    }

    fun onIncrement(counter: CounterEntity) {
        viewModelScope.launch(Dispatchers.IO) {

            _uiState.update { state ->
                val previewData = state.data.map {
                    if (it.id == counter.id) counter.copy(value = counter.value + 1) else it
                }.toCollection(ArrayList())
                state.copy(data = previewData)
            }

            _debouncer.run {
                val params = UpdateCounterParams(counter.id, counter.label, counter.value + 1)
                val result = updateCounterUseCase.execute(params)

                result.onSuccess {
                    loadAllCounters()
                }.onFailure {
                    _uiState.update {
                        it.copy(error = ErrorState.UpdateError)
                    }
                }
            }
        }
    }

    fun onDecrement(counter: CounterEntity) {
        viewModelScope.launch(Dispatchers.IO) {

            _uiState.update { state ->
                val previewData = state.data.map {
                    if (it.id == counter.id) counter.copy(value = counter.value - 1) else it
                }.toCollection(ArrayList())
                state.copy(data = previewData)
            }

            _debouncer.run {
                val params = UpdateCounterParams(counter.id, counter.label, counter.value - 1)
                val result = updateCounterUseCase.execute(params)

                result.onSuccess {
                    loadAllCounters()
                }.onFailure {
                    _uiState.update {
                        it.copy(error = ErrorState.UpdateError)
                    }
                }
            }
        }
    }

    fun onCreateCounter() {
        viewModelScope.launch(Dispatchers.IO) {
            val uuid = java.util.UUID.randomUUID().toString()
            val params = CreateCounterParams(id = uuid, label = "New Counter", 0)
            val result = createCounterUseCase.execute(params)

            result.onSuccess {
                loadAllCounters()
            }.onFailure {
                _uiState.update {
                    it.copy(error = ErrorState.CreationError)
                }
            }
        }
    }

    fun onDelete(counter: CounterEntity) {
        viewModelScope.launch(Dispatchers.IO) {

            val params = DeleteCounterParams(counter.id, counter.label, counter.value)
            val result = deleteCounterUseCase.execute(params)

            result.onSuccess {
                loadAllCounters()
            }.onFailure {
                _uiState.update {
                    it.copy(error = ErrorState.DeletionError)
                }
            }
        }
    }
}
