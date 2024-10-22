package com.iteris.counterapp.screens.home.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.iteris.counterapp.ui.theme.DefaultTypography

@Composable
fun AnimatedCounter(value: Int, oldValue: Int) {
    val valueTextStyle = MaterialTheme.typography.titleLarge

    val textColor = when (oldValue) {
        in 1..Int.MAX_VALUE -> valueTextStyle.color
        0 -> MaterialTheme.colorScheme.outline
        else -> MaterialTheme.colorScheme.error
    }

    Row(
        modifier = Modifier.animateContentSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val valueString = value.toString()
        val oldValueString = oldValue.toString()

        for (i in valueString.indices) {
            val oldChar = oldValueString.getOrNull(i)
            val newChar = valueString[i]
            val char = if (oldChar == newChar) oldValueString[i] else valueString[i]

            AnimatedContent(targetState = char, label = "Digit",
                transitionSpec = {
                    if (value < oldValue)
                        slideInVertically { it } togetherWith slideOutVertically { -it }
                    else
                        slideInVertically { -it } togetherWith slideOutVertically { it }
                }
            ) {

                Text(
                    text = it.toString(),
                    softWrap = false,
                    textAlign = TextAlign.Center,
                    style = DefaultTypography.titleLarge.copy(
                        color = textColor,
                        fontWeight = FontWeight.Bold
                    ),
                )
            }
        }
    }
}
