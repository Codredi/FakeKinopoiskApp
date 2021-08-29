package com.evanstukalov.fakekinopoiskapp.domain


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film(
    val id: Int = 0,
    val localizedName: String = "",
    val name: String = "",
    val year: Int = 0,
    val rating: Double? = null,
    val imageUrl: String? = null,
    val description: String = "",
    val genres: List<String> = listOf()
) : Parcelable

data class Genre(
    val genres: List<String> = listOf()
)

