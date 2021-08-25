package com.evanstukalov.fakekinopoiskapp.domain


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Film(
    val description: String,
    val genres: List<String>,
    val id: Int,
    val imageUrl: String,
    val localizedName: String,
    val name: String,
    val rating: Double,
    val year: Int
)