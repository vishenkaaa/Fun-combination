package com.example.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.presentation.feature.splash.SplashRoute

@Composable
fun AppNavHost(
    modifier: Modifier,
    navController: NavHostController,
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
        homeGraph(navController)
    }
}

private fun NavGraphBuilder.homeGraph(
    navController: NavController
) {
    composable<Main.Menu> {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Головне Меню")
        }
    }
}