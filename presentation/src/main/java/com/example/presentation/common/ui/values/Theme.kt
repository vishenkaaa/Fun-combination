package com.example.presentation.common.ui.values

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val ColorScheme
    @Composable
    get() = lightColorScheme(
        primary = Primary,
        onPrimary = White_80,
        secondary = Secondary,
        onSecondary = White_80,
        background = White,
        onBackground = Dark,
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