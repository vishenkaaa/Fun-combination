package com.example.domain.repository

import com.example.domain.model.HighScore
import kotlinx.coroutines.flow.Flow

interface HighScoreRepository {
    fun getAllHighScores(): Flow<List<HighScore>>
    suspend fun saveHighScore(highScore: HighScore): Boolean
}