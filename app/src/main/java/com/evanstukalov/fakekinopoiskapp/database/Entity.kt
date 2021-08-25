package com.evanstukalov.fakekinopoiskapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "database")
@TypeConverters(MyConverters::class)

class DatabaseFilm (
    @PrimaryKey val id: Int,
    val description: String,
    val genres: List<String>,
    val imageUrl: String,
    val localizedName: String,
    val name: String,
    val rating: Double,
    val year: Int
)