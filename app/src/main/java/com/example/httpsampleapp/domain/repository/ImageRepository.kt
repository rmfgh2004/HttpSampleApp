package com.example.httpsampleapp.domain.repository

import com.example.httpsampleapp.domain.model.Image

interface ImageRepository {
    suspend fun getImages(): List<Image>
}