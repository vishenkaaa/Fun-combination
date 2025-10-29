package com.example.presentation.feature.mainMenu.game.model

import com.example.domain.model.GameEmoji

data class GameState(
    val sequence: List<GameEmoji> = emptyList(),
    val userInput: List<GameEmoji> = emptyList(),
    val currentLevel: Int = 1,
    val isShowingSequence: Boolean = false,
    val isGameOver: Boolean = false,
    val currentDisplayIndex: Int = -1,
    val isWaitingForInput: Boolean = false,
    val lastPressedEmoji: GameEmoji? = null,
    val showReadyScreen: Boolean = false,
    val countdown: Int = 3
)