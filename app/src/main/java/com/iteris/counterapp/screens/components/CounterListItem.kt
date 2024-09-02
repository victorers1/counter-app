package com.iteris.counterapp.screens.components

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.iteris.counterapp.domain.entities.CounterEntity
import com.iteris.counterapp.ui.components.listitems.EditableListItem
import com.iteris.counterapp.ui.theme.CounterAppTheme

@Composable
fun CounterListItem(
    modifier: Modifier = Modifier,
    isEditing: Boolean = false,
    onDelete: () -> Unit,
    counterEntity: CounterEntity,
    counterNameMaxLength: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    onChangedHeadline: (value: String) -> Unit,
) {

    EditableListItem(
        modifier = modifier,
        headlineText = counterEntity.label.take(counterNameMaxLength),
        isEditing = isEditing,
        onDelete = onDelete,
        headLineMaxLength = counterNameMaxLength,
        onChangedHeadline = onChangedHeadline,
        trailingContent = {
            CounterButtonRow(
                value = counterEntity.value, onDecrement = onDecrement, onIncrement = onIncrement
            )
        },
    )
}

@Preview
@Composable
private fun ItemLight() {
    val counterEntity = CounterEntity(
        id = "", label = "Voltas no parque de manhã bem cedinho às 05:00 da manhã", value = 6
    )
    CounterAppTheme {
        CounterListItem(
            counterEntity = counterEntity,
            onIncrement = {},
            onDecrement = {},
            counterNameMaxLength = 50,
            onDelete = {},
            onChangedHeadline = {}
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ItemDark() {
    val counterEntity = CounterEntity(
        id = "", label = "Voltas no parque de manhã bem cedinho às 05:00 da manhã", value = 6
    )
    CounterAppTheme {
        CounterListItem(
            counterEntity = counterEntity,
            onIncrement = {},
            onDecrement = {},
            counterNameMaxLength = 50,
            onDelete = {},
            onChangedHeadline = {}
        )
    }
}

@Preview
@Composable
private fun ItemEditingLight() {
    val counterEntity = CounterEntity(
        id = "", label = "Voltas no parque de manhã bem cedinho às 05:00 da manhã", value = 6
    )
    CounterAppTheme {
        CounterListItem(
            isEditing = true,
            counterEntity = counterEntity,
            onIncrement = {},
            onDecrement = {},
            counterNameMaxLength = 50,
            onDelete = {},
            onChangedHeadline = {}
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ItemEditingDark() {
    val counterEntity = CounterEntity(
        id = "", label = "Voltas no parque de manhã bem cedinho às 05:00 da manhã", value = 6
    )
    CounterAppTheme {
        CounterListItem(
            isEditing = true,
            counterEntity = counterEntity,
            onIncrement = {},
            onDecrement = {},
            counterNameMaxLength = 50,
            onDelete = {},
            onChangedHeadline = {}
        )
    }
}
