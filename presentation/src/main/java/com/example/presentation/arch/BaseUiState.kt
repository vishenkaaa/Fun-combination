package com.example.presentation.arch

data class BaseUiState(
    val isLoading: Boolean = false,
    val error: String? = null
)