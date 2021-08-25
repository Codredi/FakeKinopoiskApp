package com.evanstukalov.fakekinopoiskapp.network

import com.evanstukalov.fakekinopoiskapp.database.DatabaseFilm
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class NetworkFilmsContainer(val films: List<NetworkFilm>)

/*Network results*/

@JsonClass(generateAdapter = true)
data class NetworkFilm(
    val description: String,
    val genres: List<String>,
    val id: Int,
    val imageUrl: String,
    val localizedName: String,
    val name: String,
    val rating: Double,
    val year: Int
)
