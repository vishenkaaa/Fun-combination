package com.example.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Graphs {
    @Serializable
    data object Splash : Graphs()

    @Serializable
    data object Main : Graphs()
}

@Serializable
sealed class Main {

    @Serializable
    data object Menu : Main()

    @Serializable
    data object Game : Main()

    @Serializable
    data object HighScore : Main()

    @Serializable
    data object PrivacyPolicy : Main()

    @Serializable
    data object GameOver : Main()
}