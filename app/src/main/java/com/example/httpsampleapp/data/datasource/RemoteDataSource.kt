package com.example.httpsampleapp.data.datasource

import com.example.httpsampleapp.data.api.ApiService
import com.example.httpsampleapp.data.model.ImageDto

class RemoteDataSource(private val api: ApiService) {
    suspend fun fetchImages(): List<ImageDto> = api.getImage()
}