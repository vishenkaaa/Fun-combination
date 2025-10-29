package com.example.data.local.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.HighScore

@Entity(tableName = "highScores")
data class HighScoreEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String,
    val score: Int,
    val createdAt: Long = System.currentTimeMillis()
)

fun HighScoreEntity.toDomain() = HighScore(
    date = date,
    score = score
)

fun HighScore.toEntity() = HighScoreEntity(
    date = date,
    score = score
)