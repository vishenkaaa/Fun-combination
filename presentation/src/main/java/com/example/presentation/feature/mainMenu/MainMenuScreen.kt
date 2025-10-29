package com.example.presentation.feature.mainMenu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.common.ui.components.DarkOverlay
import com.example.presentation.common.ui.components.MenuButton

@Composable
fun MainMenuRoute(
    onNavigateToGame: () -> Unit,
    onNavigateToHighScore: () -> Unit,
    onNavigateToPrivacyPolicy: () -> Unit,
    onExit: () -> Unit,
) {
    MainMenuScreen(
        onPlayClick = onNavigateToGame,
        onHighScoreClick = onNavigateToHighScore,
        onPrivacyPolicyClick = onNavigateToPrivacyPolicy,
        onExitClick = onExit
    )
}

@Composable
fun MainMenuScreen(
    onPlayClick: () -> Unit,
    onHighScoreClick: () -> Unit,
    onPrivacyPolicyClick: () -> Unit,
    onExitClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(R.drawable.main_bg),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.blur(16.dp)
        )

        DarkOverlay()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(bottom = 54.dp)
        ) {
            Text(
                text = stringResource(R.string.funny_combination),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(top = 160.dp)
            )

            Spacer(Modifier.weight(1f))

            MenuButton(
                text = stringResource(R.string.play),
                onClick = onPlayClick,
            )

            MenuButton(
                text = stringResource(R.string.high_score),
                onClick = onHighScoreClick,
            )

            MenuButton(
                text = stringResource(R.string.privacy_policy),
                onClick = onPrivacyPolicyClick,
            )

            Spacer(modifier = Modifier.height(16.dp))

            MenuButton(
                text = stringResource(R.string.exit),
                onClick = onExitClick,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}