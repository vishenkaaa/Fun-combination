package com.example.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class HighScore(
    val date: String,
    val score: Int
)