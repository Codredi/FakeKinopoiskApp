package com.evanstukalov.fakekinopoiskapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FilmDao {

    @Query("select * from `database` ORDER BY localizedName ASC")
    fun getFilms(): LiveData<List<DatabaseFilm>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilms(videos: List<DatabaseFilm>)

    @Query("select genres from 'database'")
    fun getGenres(): LiveData<List<DatabaseFilm>>

}