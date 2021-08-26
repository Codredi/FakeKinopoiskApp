package com.evanstukalov.fakekinopoiskapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.evanstukalov.fakekinopoiskapp.database.FilmDataBase
import com.evanstukalov.fakekinopoiskapp.database.GenresType
import com.evanstukalov.fakekinopoiskapp.domain.Film
import com.evanstukalov.fakekinopoiskapp.network.NetworkFilmsContainer
import com.evanstukalov.fakekinopoiskapp.network.RetrofitInstance
import com.evanstukalov.fakekinopoiskapp.utils.asDatabaseModel
import com.evanstukalov.fakekinopoiskapp.utils.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.lang.Exception

class Repository(private val database: FilmDataBase) {

    suspend fun refreshFilms(){
        withContext(Dispatchers.IO){
            val playList = RetrofitInstance.api.getFilms()
            database.videoDao.insertFilms(playList.asDatabaseModel())
        }
    }

    val films: LiveData<List<Film>> = Transformations.map(database.videoDao.getFilms()){
        it.asDomainModel()
    }

    val genres: LiveData<List<GenresType>> = database.videoDao.getGenres()
}