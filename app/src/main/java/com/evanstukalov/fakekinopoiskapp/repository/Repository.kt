package com.evanstukalov.fakekinopoiskapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.evanstukalov.fakekinopoiskapp.database.FilmDataBase
import com.evanstukalov.fakekinopoiskapp.domain.Film
import com.evanstukalov.fakekinopoiskapp.network.Network
import com.evanstukalov.fakekinopoiskapp.utils.asDatabaseModel
import com.evanstukalov.fakekinopoiskapp.utils.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class Repository(private val database: FilmDataBase) {

    suspend fun refreshFilms(){
        withContext(Dispatchers.IO){
            Timber.d("refresh videos is called")
            val playList = Network.apiService.getFilms()
            database.videoDao.insertFilms(playList.asDatabaseModel())
        }
    }

    val films: LiveData<List<Film>> = Transformations.map(database.videoDao.getFilms()){
        it.asDomainModel()
    }
}