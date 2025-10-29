package com.example.data.repository

import com.example.data.local.db.dao.HighScoreDao
import com.example.data.local.db.entities.toDomain
import com.example.data.local.db.entities.toEntity
import com.example.domain.model.HighScore
import com.example.domain.repository.HighScoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HighScoreRepositoryImpl @Inject constructor(
    private val highScoreDao: HighScoreDao
) : HighScoreRepository {

    override fun getAllHighScores(): Flow<List<HighScore>> =
        highScoreDao.getAllHighScores().map { list ->
            list.map { it.toDomain() }
        }

    override suspend fun saveHighScore(highScore: HighScore): Boolean {
        val currentMaxScore = highScoreDao.getMaxScore() ?: 0

        return if (highScore.score > currentMaxScore) {
            highScoreDao.insert(highScore.toEntity())
            true
        } else {
            false
        }
    }
}