package com.example.httpsampleapp.ui.screen.imagelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.httpsampleapp.domain.usecase.GetImageUseCase

class ImageListViewModelFactory(
    private val useCase: GetImageUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ImageListViewModel(useCase) as T
    }
}