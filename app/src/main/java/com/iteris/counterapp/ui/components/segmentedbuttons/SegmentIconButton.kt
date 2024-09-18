package com.iteris.counterapp.ui.components.segmentedbuttons

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.outlined.GridOn
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.iteris.counterapp.ui.theme.PreviewAppTheme

@Composable
fun SegmentIconButton(
    modifier: Modifier = Modifier,
    isActive: Boolean,
    activeIcon: ImageVector,
    inactiveIcon: ImageVector
) {
    Icon(
        modifier = modifier,
        imageVector = if (isActive) activeIcon else inactiveIcon,
        contentDescription = "${if (isActive) "Active" else "Inactive"} segment icon"
    )
}

@Preview(showBackground = true)
@Composable
private fun PrevActiveLight() {
    PreviewAppTheme {
        SegmentIconButton(
            isActive = true,
            activeIcon = Icons.Filled.Apps,
            inactiveIcon = Icons.Outlined.GridOn
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PrevInactiveLight() {
    PreviewAppTheme {
        SegmentIconButton(
            isActive = false,
            activeIcon = Icons.Filled.Apps,
            inactiveIcon = Icons.Outlined.GridOn
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PrevActiveDark() {
    PreviewAppTheme {
        SegmentIconButton(
            isActive = true,
            activeIcon = Icons.Filled.Apps,
            inactiveIcon = Icons.Outlined.GridOn
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PrevInactiveDark() {
    PreviewAppTheme {
        SegmentIconButton(
            isActive = false,
            activeIcon = Icons.Filled.Apps,
            inactiveIcon = Icons.Outlined.GridOn
        )
    }
}
