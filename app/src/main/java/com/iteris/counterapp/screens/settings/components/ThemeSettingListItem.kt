package com.iteris.counterapp.screens.settings.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iteris.counterapp.ui.components.listitems.IconListItem
import com.iteris.counterapp.ui.theme.CounterAppTheme
import com.iteris.counterapp.ui.theme.ThemeModeEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeSettingListItem(
    selectedOption: ThemeModeEntity,
    options: List<ThemeModeEntity>,
    onSelectedOption: (ThemeModeEntity) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    val width = LocalConfiguration.current.screenWidthDp
    val dropdownWidth = width.div(2)

    IconListItem(
        leadingIcon = selectedOption.icon,
        title = "Theme",
        description = "Using ${selectedOption.name}"
    ) {
        ExposedDropdownMenuBox(
            modifier = Modifier
                .wrapContentWidth()
                .widthIn(0.dp, 300.dp)
                .width(dropdownWidth.dp),
            expanded = isExpanded,
            onExpandedChange = { isExpanded = !isExpanded },
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                value = selectedOption.name, onValueChange = {},
                textStyle = MaterialTheme.typography.titleMedium,
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) }
            )
            ExposedDropdownMenu(
                expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
                options.forEach {
                    DropdownMenuItem(
                        text = { Text(text = it.name) },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        onClick = {
                            isExpanded = false
                            onSelectedOption(it)
                        },
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun PrevLight() {
    val options = listOf(ThemeModeEntity.Light, ThemeModeEntity.Dark, ThemeModeEntity.System)
    CounterAppTheme {
        ThemeSettingListItem(
            selectedOption = ThemeModeEntity.Light,
            options = options,
            onSelectedOption = {},
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PrevDark() {
    val options = listOf(ThemeModeEntity.Light, ThemeModeEntity.Dark, ThemeModeEntity.System)
    CounterAppTheme {
        ThemeSettingListItem(
            selectedOption = ThemeModeEntity.Dark,
            options = options,
            onSelectedOption = {},
        )
    }
}

@Preview
@Composable
private fun PrevSystemLight() {
    val options = listOf(ThemeModeEntity.Light, ThemeModeEntity.Dark, ThemeModeEntity.System)
    CounterAppTheme {
        ThemeSettingListItem(
            selectedOption = ThemeModeEntity.System,
            options = options,
            onSelectedOption = {},
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PrevSystemDark() {
    val options = listOf(ThemeModeEntity.Light, ThemeModeEntity.Dark, ThemeModeEntity.System)
    CounterAppTheme {
        ThemeSettingListItem(
            selectedOption = ThemeModeEntity.System,
            options = options,
            onSelectedOption = {},
        )
    }
}
