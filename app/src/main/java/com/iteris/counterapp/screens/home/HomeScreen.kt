package com.iteris.counterapp.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.iteris.counterapp.screens.components.CounterListItem
import com.iteris.counterapp.ui.compose.screen.BaseScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsState()

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
                    title = { Text(text = "Counter App") },
                    colors = TopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainer,
                        actionIconContentColor = MaterialTheme.colorScheme.onSurface,
                        titleContentColor = MaterialTheme.colorScheme.onSurface,
                        scrolledContainerColor = MaterialTheme.colorScheme.tertiary,
                        navigationIconContentColor = MaterialTheme.colorScheme.onSurface
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
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(top = 16.dp)
            ) {
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
                }
            }
        }
    }
}
