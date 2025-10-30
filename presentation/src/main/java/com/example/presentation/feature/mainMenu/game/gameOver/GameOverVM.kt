package com.example.presentation.feature.mainMenu.game.gameOver

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.example.domain.model.HighScore
import com.example.domain.repository.HighScoreRepository
import com.example.presentation.R
import com.example.presentation.arch.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class GameOverVM @Inject constructor(
    private val highScoreRepository: HighScoreRepository,
    @ApplicationContext private val context: Context
) : BaseViewModel() {

    private val _isNewHighScore = MutableStateFlow(false)
    val isNewHighScore: StateFlow<Boolean> = _isNewHighScore.asStateFlow()

    companion object {
        private var lastSavedScore: Pair<Int, Boolean>? = null
    }

    fun saveScore(score: Int) {
        lastSavedScore?.let { (savedScore, isNew) ->
            if (savedScore == score) {
                _isNewHighScore.value = isNew
                return
            }
        }

        viewModelScope.launch {
            try {
                handleLoading(true)

                val currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                val newScore = HighScore(
                    date = currentDate,
                    score = score
                )

                val isNew = highScoreRepository.saveHighScore(newScore)

                lastSavedScore = score to isNew
                _isNewHighScore.value = isNew

                handleLoading(false)
            } catch (e: Exception) {
                val error = context.getString(R.string.failed_to_save_high_score)
                handleError(Exception(error))
                handleLoading(false)
            }
        }
    }
}