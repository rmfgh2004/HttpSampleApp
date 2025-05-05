package com.example.httpsampleapp.domain.usecase

import com.example.httpsampleapp.domain.model.Image
import com.example.httpsampleapp.domain.repository.ImageRepository

class GetImageUseCase(private val repository: ImageRepository) {
    suspend operator fun invoke(): List<Image> = repository.getImages()
}