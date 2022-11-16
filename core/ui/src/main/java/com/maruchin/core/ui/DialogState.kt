package com.maruchin.core.ui

import androidx.compose.runtime.*

@Composable
fun rememberDialogState() = remember {
    DialogState()
}

@Stable
class DialogState {

    var isOpen by mutableStateOf(false)
        private set

    fun open() {
        isOpen = true
    }

    fun dismiss() {
        isOpen = false
    }
}
