package com.example.httpsampleapp.ui.screen.imagelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.httpsampleapp.domain.usecase.GetImageUseCase
import kotlinx.coroutines.launch

class ImageListViewModel(
    private val getImageUseCase: GetImageUseCase
) : ViewModel() {

    private val _state = MutableLiveData<ImageUiState>()
    val state: LiveData<ImageUiState> = _state

    fun loadImages() {
        viewModelScope.launch {
            _state.value = ImageUiState.Loading
            try {
                val images = getImageUseCase()
                _state.value = ImageUiState.Success(images)
            } catch (e: Exception) {
                _state.value = ImageUiState.Error("image loading failed: ${e.message}")
            }
        }
    }

}