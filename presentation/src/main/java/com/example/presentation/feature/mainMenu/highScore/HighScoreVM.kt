package com.example.presentation.feature.mainMenu.highScore

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
import javax.inject.Inject

@HiltViewModel
class HighScoreVM @Inject constructor(
    private val highScoreRepository: HighScoreRepository,
    @ApplicationContext private val context: Context
) : BaseViewModel() {

    private val _highScores = MutableStateFlow<List<HighScore>>(emptyList())
    val highScores: StateFlow<List<HighScore>> = _highScores.asStateFlow()

    init {
        loadHighScores()
    }

    private fun loadHighScores() {
        viewModelScope.launch {
            var firstLoad = true

            try {
                handleLoading(true)

                highScoreRepository.getAllHighScores().collect { scores ->
                    _highScores.value = scores

                    if (firstLoad) {
                        handleLoading(false)
                        firstLoad = false
                    }
                }
            } catch (e: Exception) {
                val error = context.getString(R.string.failed_to_load_high_scores)
                handleError(Exception(error))
                handleLoading(false)
            }
        }
    }
}