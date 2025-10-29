package com.example.presentation.feature.mainMenu.game.gameOver

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.HighScore
import com.example.domain.repository.HighScoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class GameOverVM @Inject constructor(
    private val highScoreRepository: HighScoreRepository
) : ViewModel() {

    private val _isNewHighScore = MutableStateFlow(false)
    val isNewHighScore: StateFlow<Boolean> = _isNewHighScore.asStateFlow()

    fun saveScore(score: Int) {
        viewModelScope.launch {
            val currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            val newScore = HighScore(
                date = currentDate,
                score = score
            )

            val isNew = highScoreRepository.saveHighScore(newScore)
            _isNewHighScore.value = isNew
        }
    }
}