package com.example.presentation.arch

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

open class BaseViewModel : ViewModel() {

    private val _baseUiState = MutableStateFlow(BaseUiState())
    val baseUiState: StateFlow<BaseUiState> = _baseUiState.asStateFlow()

    protected open fun handleLoading(isLoading: Boolean) {
        _baseUiState.update { it.copy(isLoading = isLoading) }
    }

    protected open fun handleError(
        e: Throwable
    ) {
        e.printStackTrace()
        _baseUiState.update { it.copy(error = e.message, isLoading = false) }
    }

    open fun clearErrors() {
        _baseUiState.update { it.copy(error = null) }
    }
}