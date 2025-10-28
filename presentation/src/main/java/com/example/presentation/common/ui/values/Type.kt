package com.example.presentation.common.ui.values

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.presentation.R

val gilroyFontFamily = FontFamily(
    Font(R.font.gilroy_bold, FontWeight.Bold),
    Font(R.font.gilroy_medium, FontWeight.Medium),
    Font(R.font.gilroy_regular, FontWeight.Normal)
)

val display1: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = gilroyFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp,
        color = MaterialTheme.colorScheme.onBackground
    )

val display2: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = gilroyFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 40.sp,
        color = MaterialTheme.colorScheme.onBackground
    )

val title1: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = gilroyFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 32.sp,
        color = MaterialTheme.colorScheme.onBackground
    )

val title2: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = gilroyFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        color = MaterialTheme.colorScheme.onBackground
    )

val body1: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = gilroyFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        color = MaterialTheme.colorScheme.onBackground
    )

val body2: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = gilroyFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 21.sp,
        color = MaterialTheme.colorScheme.onBackground
    )

val body3: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = gilroyFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 18.sp,
        color = MaterialTheme.colorScheme.onBackground
    )

val Typography: Typography
    @Composable
    get() = Typography(
        displayLarge = display1,
        displayMedium = display2,
        titleLarge = title1,
        titleMedium = title2,
        bodyLarge = body1,
        bodyMedium = body2,
        bodySmall = body3
    )