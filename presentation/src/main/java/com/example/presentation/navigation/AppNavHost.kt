package com.example.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.presentation.feature.mainMenu.MainMenuRoute
import com.example.presentation.feature.mainMenu.privacyPolicy.PrivacyPolicyRoute
import com.example.presentation.feature.splash.SplashRoute

@Composable
fun AppNavHost(
    modifier: Modifier,
    navController: NavHostController,
    onExit: () -> Unit
) {
    NavHost(
        modifier = modifier
            .fillMaxSize(),
        navController = navController,
        startDestination = Graphs.Splash
    ) {
        composable<Graphs.Splash> {
            SplashRoute(
                onNavigateToMenu = {
                    navController.navigate(Main.Menu) {
                        popUpTo(Graphs.Splash) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        homeGraph(navController){
            onExit()
        }
    }
}

private fun NavGraphBuilder.homeGraph(
    navController: NavController,
    onExit: () -> Unit
) {
    composable<Main.Menu> {
        MainMenuRoute(
            onNavigateToGame = {
                navController.navigate(Main.Game)
            },
            onNavigateToHighScore = {
                navController.navigate(Main.HighScore)
            },
            onNavigateToPrivacyPolicy = {
                navController.navigate(Main.PrivacyPolicy)
            },
            onExit = onExit
        )
    }

    composable<Main.Game> {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Game")
        }
    }

    composable<Main.HighScore> {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("High Score")
        }
    }

    composable<Main.PrivacyPolicy> {
       PrivacyPolicyRoute{ navController.popBackStack() }
    }

    composable<Main.GameOver> {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Game Over")
        }
    }
}