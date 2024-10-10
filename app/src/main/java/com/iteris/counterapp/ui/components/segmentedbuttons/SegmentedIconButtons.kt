package com.iteris.counterapp.ui.components.segmentedbuttons

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.outlined.GridOn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iteris.counterapp.domain.entities.aboutus.SegmentButtonEntity
import com.iteris.counterapp.ui.theme.PreviewAppTheme


@Composable
fun SegmentedIconButtons(
    selectedSegment: Int,
    segments: List<SegmentButtonEntity> = listOf(),
    onClickSegment: (Int) -> Unit
) {
    val iconSize = 24.dp
    val verticalPadding = 8.dp

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            segments.mapIndexed { index, item ->
                val isActive = index == selectedSegment

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SegmentIconButton(
                        modifier = Modifier
                            .padding(top = verticalPadding, bottom = verticalPadding.div(2))
                            .size(iconSize)
                            .clickable { onClickSegment(index) },
                        isActive = isActive,
                        activeIcon = item.activeIcom,
                        inactiveIcon = item.inactiveIcon
                    )

                    if (isActive)
                        Box(
                            modifier = Modifier
                                .width(iconSize.times(1.5f))
                                .height(2.dp)
                                .background(color = MaterialTheme.colorScheme.onSurface)
                        )
                }
            }
        }

        HorizontalDivider()
    }
}

@Preview(showBackground = true)
@Composable
private fun PrevLight() {
    val segments = listOf(
        SegmentButtonEntity("", Icons.Filled.Apps, Icons.Outlined.GridOn),
        SegmentButtonEntity("", Icons.Filled.Apps, Icons.Outlined.GridOn),
        SegmentButtonEntity("", Icons.Filled.Apps, Icons.Outlined.GridOn),
    )
    PreviewAppTheme {
        SegmentedIconButtons(
            selectedSegment = 0,
            segments = segments,
            onClickSegment = {}
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PrevDark() {
    val segments = listOf(
        SegmentButtonEntity("", Icons.Filled.Apps, Icons.Outlined.GridOn),
        SegmentButtonEntity("", Icons.Filled.Apps, Icons.Outlined.GridOn),
        SegmentButtonEntity("", Icons.Filled.Apps, Icons.Outlined.GridOn),
    )
    PreviewAppTheme {
        SegmentedIconButtons(
            selectedSegment = 0,
            segments = segments,
            onClickSegment = {}
        )
    }
}