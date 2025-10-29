package com.example.domain.model

enum class GameEmoji(val emoji: String) {
    SMILE("😊"),
    HEART("❤️"),
    STAR("⭐"),
    FIRE("🔥"),
    ROCKET("🚀");

    companion object {
        fun random() = entries.random()
    }
}