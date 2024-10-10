package com.iteris.counterapp.ui.components.segmentedbuttons

import android.content.res.Configuration
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.platform.LocalConfiguration
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

    val screenWidth: Int = LocalConfiguration.current.screenWidthDp
    val bottomBarSize = iconSize.value * 1.5

    val middleOfFirstBar = screenWidth / segments.size / 2
    val bottomBarOffsets =
        List(segments.size) { index ->
            if (index == 0) middleOfFirstBar else (3 + (index - 1) * 2) * middleOfFirstBar
        }.map { offset ->
            offset - bottomBarSize / 2
        }

    val bottomBarCurrentOffset = animateDpAsState(
        targetValue = bottomBarOffsets[selectedSegment].dp,
        label = ""
    )


    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            segments.mapIndexed { index, item ->
                val isActive = index == selectedSegment

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    SegmentIconButton(
                        modifier = Modifier
                            .padding(top = verticalPadding, bottom = verticalPadding.div(2))
                            .size(iconSize)
                            .clickable { onClickSegment(index) },
                        isActive = isActive,
                        activeIcon = item.activeIcom,
                        inactiveIcon = item.inactiveIcon
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .offset(x = bottomBarCurrentOffset.value, y = 0.dp)
                .width(bottomBarSize.dp)
                .height(2.dp)
                .background(color = MaterialTheme.colorScheme.onSurface)
        )

        HorizontalDivider()
    }
}


val segments = listOf(
    SegmentButtonEntity("", Icons.Filled.Apps, Icons.Outlined.GridOn),
    SegmentButtonEntity("", Icons.Filled.Apps, Icons.Outlined.GridOn),
    SegmentButtonEntity("", Icons.Filled.Apps, Icons.Outlined.GridOn),
    SegmentButtonEntity("", Icons.Filled.Apps, Icons.Outlined.GridOn),
    SegmentButtonEntity("", Icons.Filled.Apps, Icons.Outlined.GridOn),
)

@Preview(showBackground = true)
@Composable
private fun PrevLight0() {
    PreviewAppTheme {
        SegmentedIconButtons(
            selectedSegment = 0,
            segments = segments,
            onClickSegment = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PrevLight1() {
    PreviewAppTheme {
        SegmentedIconButtons(
            selectedSegment = 1,
            segments = segments,
            onClickSegment = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PrevLight2() {
    PreviewAppTheme {
        SegmentedIconButtons(
            selectedSegment = 2,
            segments = segments,
            onClickSegment = {}
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PrevDark0() {
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
private fun PrevDark1() {
    PreviewAppTheme {
        SegmentedIconButtons(
            selectedSegment = 1,
            segments = segments,
            onClickSegment = {}
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PrevDark2() {
    PreviewAppTheme {
        SegmentedIconButtons(
            selectedSegment = 2,
            segments = segments,
            onClickSegment = {}
        )
    }
}
