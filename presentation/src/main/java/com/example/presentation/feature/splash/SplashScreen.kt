package com.example.presentation.feature.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.presentation.R
import com.example.presentation.common.ui.values.FunnyCombinationTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

@Composable
fun SplashRoute(
    onNavigateToMenu: () -> Unit
) {
    SplashScreen {
        onNavigateToMenu()
    }
}

@Composable
fun SplashScreen(
    onFinished: () -> Unit = {}
) {
    val bgAlpha = remember { Animatable(0.6f) }
    val logoAlpha = remember { Animatable(0f) }
    val logoScale = remember { Animatable(0.5f) }
    val logoTranslationY = remember { Animatable(0f) }

    val textAlpha = remember { Animatable(0f) }
    val textTranslationY = remember { Animatable(50f) }

    LaunchedEffect(Unit) {
        launch {
            delay(1000)
            bgAlpha.animateTo(
                1f,
                animationSpec = tween(800, easing = LinearEasing)
            )
        }

        val logoScaleJob = launch {
            logoScale.animateTo(
                1f,
                animationSpec = tween(1000, delayMillis = 200, easing = FastOutSlowInEasing)
            )
        }
        val logoAlphaJob = launch {
            logoAlpha.animateTo(
                1f,
                animationSpec = tween(800, delayMillis = 300)
            )
        }
        val logoTranslateJob = launch {
            logoTranslationY.animateTo(
                -20f,
                animationSpec = tween(1000, delayMillis = 200, easing = FastOutSlowInEasing)
            )
            logoTranslationY.animateTo(
                0f,
                animationSpec = tween(800, easing = FastOutSlowInEasing)
            )
        }

        joinAll(logoScaleJob, logoAlphaJob, logoTranslateJob)

        val textTranslationJob = launch {
            textTranslationY.animateTo(
                0f,
                animationSpec = tween(800, easing = FastOutSlowInEasing)
            )
        }
        val textAlphaJob = launch {
            textAlpha.animateTo(
                1f,
                animationSpec = tween(600, delayMillis = 100)
            )
        }

        joinAll(textTranslationJob, textAlphaJob)

        delay(1500)
        onFinished()
    }

    val annotatedText = buildAnnotatedString {
        withStyle(style = SpanStyle(
            fontSize = MaterialTheme.typography.displayLarge.fontSize,
            color = MaterialTheme.colorScheme.background,
            fontWeight = MaterialTheme.typography.displayLarge.fontWeight
        )){
            append("Funny")
        }
        withStyle(style = SpanStyle(
            fontSize = MaterialTheme.typography.displayMedium.fontSize,
            color = MaterialTheme.colorScheme.background,
            fontWeight = MaterialTheme.typography.displayMedium.fontWeight,
        )){
            append("\nCombination")
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.splash_bg),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .alpha(bgAlpha.value),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(200.dp)
                    .alpha(logoAlpha.value)
                    .scale(logoScale.value)
            )

            Text(
                modifier = Modifier
                    .alpha(textAlpha.value)
                    .fillMaxWidth(),
                text = annotatedText,
                textAlign = TextAlign.Center,
                style = TextStyle(lineHeight = 3.5.em)
            )
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    FunnyCombinationTheme {
        SplashScreen()
    }
}