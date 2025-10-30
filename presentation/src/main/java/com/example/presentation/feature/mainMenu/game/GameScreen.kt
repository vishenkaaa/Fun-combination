package com.example.presentation.feature.mainMenu.game

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.domain.model.GameEmoji
import com.example.presentation.R
import com.example.presentation.common.ui.components.AppToolbar
import com.example.presentation.common.ui.components.DarkOverlay
import com.example.presentation.common.ui.components.EmojiButton
import com.example.presentation.feature.mainMenu.game.model.GameState

@Composable
fun GameRoute(
    onNavigateBack: () -> Unit,
    onNavigateToGameOver: (score: Int) -> Unit,
    viewModel: GameViewModel = viewModel()
) {
    val gameState by viewModel.gameState.collectAsState()

    if (gameState.isGameOver) {
        onNavigateToGameOver(viewModel.getScore())
    }

    GameScreen(
        gameState = gameState,
        onEmojiPressed = viewModel::onEmojiPressed,
        onNavigateBack = onNavigateBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(
    gameState: GameState,
    onEmojiPressed: (GameEmoji) -> Unit,
    onNavigateBack: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.splash_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        DarkOverlay()

        Scaffold(
            topBar = {
                AppToolbar(stringResource(R.string.level, gameState.currentLevel)) {
                    onNavigateBack()
                }
            },
            containerColor = Color.Transparent
        ) { paddingValues ->
            AnimatedContent(
                targetState = gameState.showReadyScreen,
                label = "GameScreenStateTransition",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                transitionSpec = {
                    fadeIn(animationSpec = tween(durationMillis = 500)) togetherWith
                            fadeOut(animationSpec = tween(durationMillis = 500))
                }
            ) { showReadyScreen ->
                if (showReadyScreen) {
                    ReadyScreen(countdown = gameState.countdown)
                } else {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            ProgressSection(
                                currentProgress = gameState.userInput.size,
                                totalProgress = gameState.sequence.size,
                                isShowingSequence = gameState.isShowingSequence
                            )

                            Spacer(modifier = Modifier.weight(1f))

                            AnimatedVisibility(
                                visible = gameState.isWaitingForInput,
                                enter = slideInVertically(
                                    initialOffsetY = { it }
                                ) + fadeIn(animationSpec = tween(durationMillis = 300)),

                                exit = slideOutVertically(
                                    targetOffsetY = { it }
                                ) + fadeOut(animationSpec = tween(durationMillis = 300))
                            ) {
                                EmojiButtonsPanel(
                                    onEmojiPressed = onEmojiPressed,
                                    enabled = gameState.isWaitingForInput && !gameState.isGameOver,
                                )
                            }
                        }

                        DisplayArea(
                            gameState = gameState
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ProgressSection(
    currentProgress: Int,
    totalProgress: Int,
    isShowingSequence: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = if (isShowingSequence) stringResource(R.string.watch_carefully)
            else stringResource(R.string.your_turn),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary,

        )

        if (!isShowingSequence && totalProgress > 0) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(totalProgress) { index ->
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .clip(CircleShape)
                            .background(
                                if (index < currentProgress)
                                    MaterialTheme.colorScheme.primary
                                else
                                    MaterialTheme.colorScheme.background.copy(alpha = 0.6f)
                            )
                    )
                }
            }
            Text(
                text = "$currentProgress / $totalProgress",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.background.copy(alpha = 0.4f)
            )
        }
    }
}

@Composable
private fun DisplayArea(
    gameState: GameState
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 150.dp)
            .height(280.dp)
            .padding(16.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(MaterialTheme.colorScheme.background.copy(alpha = 0.1f))
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.background.copy(alpha = 0.5f),
                shape = RoundedCornerShape(24.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        if (gameState.isShowingSequence && gameState.currentDisplayIndex >= 0) {
            val emoji = gameState.sequence.getOrNull(gameState.currentDisplayIndex)
            emoji?.let {
                key("sequence_${gameState.currentDisplayIndex}_${it.emoji}") {
                    AnimatedEmoji(emoji = it.emoji)
                }
            }
        }

        if (gameState.lastPressedEmoji != null && !gameState.isShowingSequence) {
            key("pressed_${gameState.lastPressedEmoji.emoji}") {
                AnimatedEmoji(emoji = gameState.lastPressedEmoji.emoji)
            }
        }

        if ((!gameState.isShowingSequence && gameState.lastPressedEmoji == null) ||
            (gameState.isShowingSequence && gameState.currentDisplayIndex == -1)) {
            Text(
                text = stringResource(R.string.question_mark),
                style = MaterialTheme.typography.displayMedium.copy(fontSize = 80.sp),
                color = MaterialTheme.colorScheme.background.copy(alpha = 0.4f)
            )
        }
    }
}

@Composable
private fun AnimatedEmoji(emoji: String) {
    AnimatedVisibility(
        visible = true,
        enter = fadeIn(tween(200)) + scaleIn(tween(200)),
        exit = fadeOut(tween(200)) + scaleOut(tween(200))
    ) {
        Text(
            text = emoji,
            fontSize = 120.sp
        )
    }
}

@Composable
private fun EmojiButtonsPanel(
    onEmojiPressed: (GameEmoji) -> Unit,
    enabled: Boolean,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally)
        ) {
            GameEmoji.entries.take(3).forEach { emoji ->
                EmojiButton(
                    emoji = emoji,
                    onClick = { onEmojiPressed(emoji) },
                    enabled = enabled,
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally)
        ) {
            GameEmoji.entries.drop(3).forEach { emoji ->
                EmojiButton(
                    emoji = emoji,
                    onClick = { onEmojiPressed(emoji) },
                    enabled = enabled,
                )
            }
        }
    }
}

@Composable
private fun ReadyScreen(countdown: Int) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = stringResource(R.string.get_ready),
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )

            AnimatedVisibility(
                visible = true,
                enter = scaleIn(tween(300)) + fadeIn(tween(300)),
                exit = scaleOut(tween(300)) + fadeOut(tween(300))
            ) {
                Text(
                    text = countdown.toString(),
                    fontSize = 120.sp,
                    style = MaterialTheme.typography.displayLarge.copy(fontSize = 80.sp),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}