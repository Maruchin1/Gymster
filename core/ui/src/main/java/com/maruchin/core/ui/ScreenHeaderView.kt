package com.maruchin.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun ScreenHeaderView(
    screenName: String,
    onNavigateUp: (() -> Unit)? = null,
    onOpenOptions: (() -> Unit)? = null,
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (onNavigateUp != null) {
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = onNavigateUp) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = "Navigate up"
                    )
                }
            }
            Text(
                text = screenName,
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            if (onOpenOptions != null) {
                IconButton(onClick = onOpenOptions) {
                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Open option")
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
        Box(
            modifier = Modifier
                .height(8.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colors.primary)
        )
    }
}
