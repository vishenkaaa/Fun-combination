package com.example.presentation

import androidx.lifecycle.viewModelScope
import com.example.presentation.arch.BaseViewModel
import com.example.presentation.navigation.Graphs
import com.example.presentation.navigation.Main
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
) : BaseViewModel() {

    private val _uiEvents = MutableSharedFlow<UiEvent>()
    val uiEvents: SharedFlow<UiEvent> = _uiEvents.asSharedFlow()

    private var lastBackPressTime: Long = 0
    private var currentRoute: String? = null

    fun onBackPressed() {
        viewModelScope.launch {
            if (isDestinationRoot(currentRoute)) {
                val currentTime = System.currentTimeMillis()
                val timeDiff = currentTime - lastBackPressTime

                if (timeDiff < 2000) {
                    _uiEvents.emit(UiEvent.ExitApp)
                } else {
                    lastBackPressTime = currentTime
                    _uiEvents.emit(UiEvent.ShowExitMessage)
                }
            } else {
                _uiEvents.emit(UiEvent.NavigateBack)
            }
        }
    }

    fun onDestinationChanged(route: String?) {
        currentRoute = route
    }

    private fun isDestinationRoot(route: String?): Boolean {
        if (route == null) return false

        return route == Main.Menu::class.qualifiedName
    }
}

sealed class UiEvent {
    data object ExitApp : UiEvent()
    data object ShowExitMessage : UiEvent()
    data object NavigateBack : UiEvent()
}