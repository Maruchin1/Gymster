package com.maruchin.ui.set

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
internal fun SelectableValue(text: String, selected: Boolean, onSelect: () -> Unit) {
    Box(
        modifier = Modifier
            .run {
                if (selected) border(
                    width = 4.dp,
                    color = MaterialTheme.colors.primary,
                    shape = RoundedCornerShape(100),
                ) else this
            }
            .clickable { onSelect() }
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.h4,
            maxLines = 1,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 20.dp),
            fontWeight = FontWeight.Medium,
        )
    }
}
