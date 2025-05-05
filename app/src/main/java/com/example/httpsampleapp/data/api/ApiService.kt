package com.example.httpsampleapp.data.api

import com.example.httpsampleapp.data.model.ImageDto
import retrofit2.http.GET

interface ApiService {
    @GET("v2/list")
    suspend fun getImage(): List<ImageDto>
}