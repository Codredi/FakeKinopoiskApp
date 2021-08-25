package com.evanstukalov.fakekinopoiskapp.domain


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film(
    val description: String,
    val genres: List<String>,
    val id: Int,
    val imageUrl: String,
    val localizedName: String,
    val name: String,
    val rating: Double,
    val year: Int
) : Parcelable