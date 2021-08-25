package com.evanstukalov.fakekinopoiskapp.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FilmDao {

    @Query("select * from `database`")
    fun getFilms(): LiveData<List<DatabaseFilm>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilms(videos: List<DatabaseFilm>)

}

@Database(entities = [DatabaseFilm::class], version = 1)
abstract class FilmDataBase: RoomDatabase(){
    abstract val videoDao: FilmDao
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
