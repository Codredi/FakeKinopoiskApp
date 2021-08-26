package com.evanstukalov.fakekinopoiskapp.network

import com.evanstukalov.fakekinopoiskapp.database.DatabaseFilm
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class NetworkFilmsContainer(val films: List<NetworkFilm>)

/*Network results*/

@JsonClass(generateAdapter = true)
data class NetworkFilm(
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "localized_name")
    val localizedName: String = "",
    @Json(name = "name")
    val name: String = "",
    @Json(name = "year")
    val year: Int = 0,
    @Json(name = "rating")
    val rating: Double? = null,
    @Json(name = "image_url")
    val imageUrl: String? = null,
    @Json(name = "description")
    val description: String = "",
    @Json(name = "genres")
    val genres: List<String> = listOf()
)