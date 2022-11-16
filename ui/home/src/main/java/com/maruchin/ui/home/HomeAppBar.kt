package com.maruchin.ui.home

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.maruchin.core.ui.GymsterAppBar

@Composable
internal fun HomeAppBar(state: HomeUiState, onOpenProfile: () -> Unit) {
    GymsterAppBar(
        title = state.planName,
        actions = {
            IconButton(
                onClick = onOpenProfile
            ) {
                Icon(
                    imageVector = Icons.Outlined.AccountCircle,
                    contentDescription = stringResource(R.string.account),
                    tint = colors.onBackground,
                )
            }
        }
    )
}
