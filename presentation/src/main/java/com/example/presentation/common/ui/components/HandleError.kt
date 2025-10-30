package com.example.presentation.common.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.presentation.arch.BaseUiState

@Composable
fun HandleError(
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    baseUiState: BaseUiState,
    onErrorConsume: (() -> Unit)? = null
) {
    val error = baseUiState.error ?: return

    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .padding(bottom = 16.dp)
            .zIndex(5f),
        contentAlignment = Alignment.BottomCenter
    ) {
        ErrorSnackBar(
            snackBarHostState = snackbarHostState,
            error = error,
            onErrorDismissed = onErrorConsume,
        )
    }
}