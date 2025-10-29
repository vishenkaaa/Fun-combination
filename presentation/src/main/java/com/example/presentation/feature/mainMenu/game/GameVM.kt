package com.example.presentation.feature.mainMenu.game

import androidx.lifecycle.viewModelScope
import com.example.domain.model.GameEmoji
import com.example.presentation.arch.BaseViewModel
import com.example.presentation.feature.mainMenu.game.model.GameState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameViewModel : BaseViewModel() {

    private val _gameState = MutableStateFlow(GameState())
    val gameState: StateFlow<GameState> = _gameState.asStateFlow()

    init {
        startNewGame()
    }

    private fun startNewGame() {
        _gameState.update {
            GameState(
                currentLevel = 1,
                sequence = emptyList(),
                userInput = emptyList(),
                isShowingSequence = false,
                isGameOver = false,
                currentDisplayIndex = -1,
                isWaitingForInput = false,
                showReadyScreen = false,
                countdown = 3
            )
        }
        startNextLevel()
    }

    private fun startNextLevel() {
        viewModelScope.launch {
            _gameState.update {
                it.copy(
                    showReadyScreen = true,
                    countdown = 3,
                    isWaitingForInput = false
                )
            }

            repeat(3) { index ->
                delay(1000)
                _gameState.update { it.copy(countdown = 2 - index) }
            }

            delay(1000)

            val newSequence = _gameState.value.sequence.toMutableList().apply {
                add(GameEmoji.random())
            }

            _gameState.update {
                it.copy(
                    showReadyScreen = false,
                    sequence = newSequence,
                    userInput = emptyList(),
                    isShowingSequence = true,
                    isWaitingForInput = false,
                    currentDisplayIndex = -1,
                    lastPressedEmoji = null
                )
            }

            delay(500)
            newSequence.forEachIndexed { index, _ ->
                _gameState.update { it.copy(currentDisplayIndex = index) }
                delay(800)

                _gameState.update { it.copy(currentDisplayIndex = -1) }
                delay(300)
            }

            delay(200)
            _gameState.update {
                it.copy(
                    isShowingSequence = false,
                    currentDisplayIndex = -1,
                    isWaitingForInput = true
                )
            }
        }
    }

    fun onEmojiPressed(emoji: GameEmoji) {
        if (!_gameState.value.isWaitingForInput || _gameState.value.isGameOver) {
            return
        }

        val currentState = _gameState.value
        val newUserInput = currentState.userInput + emoji

        _gameState.update { it.copy(lastPressedEmoji = emoji) }

        viewModelScope.launch {
            delay(300)
            _gameState.update { it.copy(lastPressedEmoji = null) }
        }

        val isCorrect = currentState.sequence
            .take(newUserInput.size)
            .zip(newUserInput)
            .all { (expected, actual) -> expected == actual }

        if (!isCorrect) {
            _gameState.update {
                it.copy(
                    userInput = newUserInput,
                    isGameOver = true,
                    isWaitingForInput = false
                )
            }
            return
        }

        _gameState.update { it.copy(userInput = newUserInput) }

        if (newUserInput.size == currentState.sequence.size) {
            viewModelScope.launch {
                delay(500)
                _gameState.update { it.copy(currentLevel = it.currentLevel + 1) }
                delay(500)
                startNextLevel()
            }
        }
    }

    fun getScore(): Int {
        return _gameState.value.currentLevel - 1
    }
}