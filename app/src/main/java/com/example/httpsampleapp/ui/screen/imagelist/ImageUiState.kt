package com.example.httpsampleapp.ui.screen.imagelist

import com.example.httpsampleapp.domain.model.Image

sealed class ImageUiState {
    object Loading : ImageUiState()
    data class Success(val images: List<Image>) : ImageUiState()
    data class Error(val message: String) : ImageUiState()
}
