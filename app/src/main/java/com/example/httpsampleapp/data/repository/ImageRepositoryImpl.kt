package com.example.httpsampleapp.data.repository

import com.example.httpsampleapp.data.datasource.RemoteDataSource
import com.example.httpsampleapp.data.mapper.toDomain
import com.example.httpsampleapp.domain.model.Image
import com.example.httpsampleapp.domain.repository.ImageRepository

class ImageRepositoryImpl(private val remote: RemoteDataSource) : ImageRepository {
    override suspend fun getImages(): List<Image> {
        return remote.fetchImages().map { it.toDomain() }
    }
}