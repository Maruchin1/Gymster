package com.maruchin.ui.training

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.SportsScore
import androidx.compose.runtime.Composable
import com.maruchin.core.ui.GymsterAppBar

@Composable
internal fun TrainingAppBar(
    title: String,
    onFinish: () -> Unit
) {
    GymsterAppBar(
        title = title,
        actions = {
            IconButton(onClick = onFinish) {
                Icon(imageVector = Icons.Outlined.SportsScore, contentDescription = null)
            }
        }
    )
}
