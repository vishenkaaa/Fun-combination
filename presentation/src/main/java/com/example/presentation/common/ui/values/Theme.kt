package com.example.presentation.common.ui.values

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val ColorScheme
    @Composable
    get() = lightColorScheme(
        primary = Orange,
        onPrimary = Dark,
        secondary = LightGray,
        background = Background,
        onBackground = Dark,
        primaryContainer = Dark,
        onPrimaryContainer = Dark,
        error = Red,
        onError = Dark,
        surfaceVariant = Shadow
    )

@Composable
fun FunnyCombinationTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = ColorScheme,
        typography = Typography,
        content = content
    )
}