package com.example.presentation.common.ui.values

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.presentation.R

val Primary: Color
    @Composable
    get() = colorResource(R.color.primary)

val Secondary: Color
    @Composable
    get() = colorResource(R.color.secondary)

val Shadow: Color
    @Composable
    get() = colorResource(R.color.shadow)

val Background: Color
    @Composable
    get() = colorResource(R.color.background)

val Dark: Color
    @Composable
    get() = colorResource(R.color.dark)

val Red: Color
    @Composable
    get() = colorResource(R.color.red)