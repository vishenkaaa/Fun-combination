package com.example.data.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.data.local.db.entities.HighScoreEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HighScoreDao {

    @Query("SELECT * FROM highScores ORDER BY score DESC, createdAt DESC")
    fun getAllHighScores(): Flow<List<HighScoreEntity>>

    @Query("SELECT MAX(score) FROM highScores")
    suspend fun getMaxScore(): Int?

    @Insert
    suspend fun insert(highScore: HighScoreEntity): Long

    @Delete
    suspend fun delete(highScore: HighScoreEntity)
}