package com.iteris.counterapp.screens.aboutus.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.AssignmentInd
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.outlined.AssignmentInd
import androidx.compose.material.icons.outlined.GridOn
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.runtime.Composable
import com.iteris.counterapp.domain.entities.aboutus.SegmentButtonEntity
import com.iteris.counterapp.ui.components.segmentedbuttons.SegmentedIconButtons

@Composable
fun AboutUsSegmentedButtons(selectedSegment: Int, onClickSegment: (Int) -> Unit) {
    val segments = listOf(
        SegmentButtonEntity("posts", Icons.Filled.Apps, Icons.Outlined.GridOn),
        SegmentButtonEntity("videos", Icons.Filled.Movie, Icons.Outlined.Movie),
        SegmentButtonEntity("mentions", Icons.Filled.AssignmentInd, Icons.Outlined.AssignmentInd),
    )

    SegmentedIconButtons(
        selectedSegment = selectedSegment,
        segments = segments,
        onClickSegment = onClickSegment
    )
}
