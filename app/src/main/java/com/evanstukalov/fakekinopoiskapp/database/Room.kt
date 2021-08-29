package com.evanstukalov.fakekinopoiskapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DatabaseFilm::class], version = 1, exportSchema = false)
abstract class FilmDataBase: RoomDatabase(){
    abstract val filmDao: FilmDao
}

private lateinit var INSTANCE: FilmDataBase

fun getDatabase(context: Context): FilmDataBase {
    synchronized(FilmDataBase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                FilmDataBase::class.java,
                "videos").build()
        }
    }
    return INSTANCE
}
