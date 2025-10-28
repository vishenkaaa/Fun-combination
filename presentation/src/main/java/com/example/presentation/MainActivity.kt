package com.example.presentation

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.SystemBarStyle
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.presentation.common.ui.values.FunnyCombinationTheme
import com.example.presentation.navigation.AppNavHost
import kotlinx.coroutines.launch

class MainActivity : FragmentActivity() {
    private var navController: NavHostController? = null
    private val mainVM: MainVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT,
                Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT,
                Color.TRANSPARENT
            )
        )

        observeUiEvents()
        setContent()
    }

    private fun observeUiEvents() {
        lifecycleScope.launch {
            mainVM.uiEvents.collect { event ->
                when (event) {
                    is UiEvent.ExitApp -> finish()
                    is UiEvent.ShowExitMessage -> {
                        Toast.makeText(this@MainActivity,
                            getString(R.string.press_again_to_exit), Toast.LENGTH_SHORT).show()
                    }
                    is UiEvent.NavigateBack -> {
                        navController?.popBackStack()
                    }
                }
            }
        }
    }

    private fun setContent(){
        setContent{
            val snackbarHostState = remember { SnackbarHostState() }
            navController = rememberNavController()

            FunnyCombinationTheme {
                LaunchedEffect(navController) {
                    navController!!.addOnDestinationChangedListener { _, destination, _ ->
                        mainVM.onDestinationChanged(destination.route)
                    }
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    BackHandler{
                        mainVM.onBackPressed()
                    }

                    Scaffold(
                        snackbarHost = { SnackbarHost(snackbarHostState) },
                        contentWindowInsets = WindowInsets(0.dp),
                        content = { paddingValues ->
                            Box(modifier = Modifier.padding(paddingValues)) {

                                AppNavHost(
                                    modifier = Modifier.fillMaxSize(),
                                    navController = navController!!,
                                    onExit = { finish() }
                                )
                            }
                        },
                    )
                }
            }
        }
    }
}