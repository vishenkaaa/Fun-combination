package com.example.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.example.presentation.feature.mainMenu.MainMenuRoute
import com.example.presentation.feature.mainMenu.game.GameRoute
import com.example.presentation.feature.mainMenu.game.gameOver.GameOverRoute
import com.example.presentation.feature.mainMenu.highScore.HighScoreRoute
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
                    navController.navigate(Graphs.Main) {
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
    navigation<Graphs.Main>(
        startDestination = Main.Menu
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
            GameRoute(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToGameOver = { score ->
                    navController.navigate(Main.GameOver(score = score)) {
                        popUpTo(Main.Menu) {
                            inclusive = false
                        }
                    }
                }
            )
        }

        composable<Main.HighScore> { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry<Graphs.Main>()
            }
            HighScoreRoute(
                viewModel = hiltViewModel(parentEntry),
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable<Main.PrivacyPolicy> {
            PrivacyPolicyRoute { navController.popBackStack() }
        }

        composable<Main.GameOver> { backStackEntry ->
            val gameOver: Main.GameOver = backStackEntry.toRoute()

            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry<Graphs.Main>()
            }

            GameOverRoute(
                score = gameOver.score,
                onNavigateToMenu = {
                    navController.navigate(Main.Menu) {
                        popUpTo(Main.Menu) {
                            inclusive = true
                        }
                    }
                },
                onPlayAgain = {
                    navController.navigate(Main.Game) {
                        popUpTo(Main.Menu) {
                            inclusive = false
                        }
                    }
                },
                viewModel = hiltViewModel(parentEntry)
            )
        }
    }
}