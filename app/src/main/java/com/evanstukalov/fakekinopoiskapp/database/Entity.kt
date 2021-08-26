package com.evanstukalov.fakekinopoiskapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.squareup.moshi.Json

@Entity(tableName = "database")
@TypeConverters(MyConverters::class)
data class DatabaseFilm (
    @PrimaryKey
    val id: Int = 0,
    val localizedName: String = "",
    val name: String = "",
    val year: Int = 0,
    val rating: Double? = null,
    val imageUrl: String? = null,
    val description: String = "",
    val genres: List<String> = listOf()
)


