package com.example.httpsampleapp.data.mapper

import com.example.httpsampleapp.data.model.ImageDto
import com.example.httpsampleapp.domain.model.Image

fun ImageDto.toDomain(): Image {
    return Image(id = id, author = author, imageUrl = download_url)
}