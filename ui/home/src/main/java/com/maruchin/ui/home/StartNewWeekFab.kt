package com.maruchin.ui.home

import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
internal fun StartNewWeekFab(onClick: () -> Unit) {
    ExtendedFloatingActionButton(
        text = { Text(text = stringResource(R.string.new_week).uppercase()) },
        onClick = onClick,
        icon = { Icon(imageVector = Icons.Outlined.CalendarMonth, contentDescription = null) },
        backgroundColor = colors.primary,
    )
}
