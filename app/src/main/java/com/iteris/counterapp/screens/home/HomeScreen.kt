package com.iteris.counterapp.screens.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.iteris.counterapp.screens.home.components.CounterListItem
import com.iteris.counterapp.ui.compose.screen.BaseScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsStateWithLifecycle()

    LaunchedEffect(lifecycleState) {
        when (lifecycleState) {
            Lifecycle.State.DESTROYED -> {}
            Lifecycle.State.INITIALIZED -> {}
            Lifecycle.State.CREATED -> {}
            Lifecycle.State.STARTED -> {}
            Lifecycle.State.RESUMED -> {
                viewModel.loadAllCounters()
            }
        }
    }

    BaseScreen(
        loading = uiState.value.isLoading,
        error = uiState.value.error,
        onRetry = {},
        onErrorClosed = {},
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text(text = "Home") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainer,
                    ),
                    actions = {
                        if (uiState.value.isEditing)
                            IconButton(onClick = viewModel::onDoneEdit) {
                                Icon(
                                    imageVector = Icons.Filled.Check,
                                    contentDescription = "Edit counters"
                                )
                            }
                        else
                            IconButton(onClick = viewModel::onStartEdit) {
                                Icon(
                                    imageVector = Icons.Filled.Edit,
                                    contentDescription = "Edit counters"
                                )
                            }
                    },
                )
            },
            floatingActionButton = {
                if (!uiState.value.isEditing)
                    FloatingActionButton(onClick = viewModel::onCreateCounter) {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add a counter")
                    }
            },
        ) { innerPadding ->
            LazyColumn(
                contentPadding = innerPadding,
            ) {

                item { Spacer(modifier = Modifier.height(8.dp)) }

                items(count = uiState.value.data.size) {
                    val counter = uiState.value.data[it]
                    key(counter.id) {
                        CounterListItem(
                            isEditing = uiState.value.isEditing,
                            counterEntity = counter,
                            onDelete = { viewModel.onDelete(counter) },
                            onIncrement = { viewModel.onIncrement(counter) },
                            onDecrement = { viewModel.onDecrement(counter) },
                            counterNameMaxLength = uiState.value.counterNameMaxLength,
                            onChangedHeadline = { headline ->
                                viewModel.onEditedHeadline(counter.id, headline)
                            }
                        )
                    }

                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                }

                item { Spacer(modifier = Modifier.height(64.dp)) }
            }
        }
    }
}
