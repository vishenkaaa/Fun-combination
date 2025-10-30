package com.example.presentation.common.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun ErrorSnackBar(
    snackBarHostState: SnackbarHostState,
    error: String,
    onErrorDismissed: (() -> Unit)? = null,
) {
    val noActionLabel: String? = null

    LaunchedEffect(error, snackBarHostState) {
        val action = snackBarHostState.showSnackbar(
            message = error,
            actionLabel = noActionLabel,
            duration = SnackbarDuration.Short,
            withDismissAction = true
        )
        when (action) {
            SnackbarResult.Dismissed -> {
                onErrorDismissed?.invoke()
            }

            SnackbarResult.ActionPerformed -> {}
        }
    }

    SnackbarHost(
        hostState = snackBarHostState,
    ) { snackbarData ->
        Snackbar(
            modifier = Modifier
                .padding(bottom = 60.dp)
                .padding(horizontal = 16.dp)
                .zIndex(1f),
            shape = RoundedCornerShape(16.dp),
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            action = null,
            dismissAction = if (snackbarData.visuals.withDismissAction
            ) {
                {
                    IconButton(
                        onClick = { snackbarData.dismiss() }) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "Dismiss",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            } else null
        ) {
            Text(
                text = snackbarData.visuals.message,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(end = 0.dp)
            )
        }
    }
}