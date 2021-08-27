package com.evanstukalov.fakekinopoiskapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.evanstukalov.fakekinopoiskapp.database.DatabaseGenre
import com.evanstukalov.fakekinopoiskapp.database.FilmDataBase
import com.evanstukalov.fakekinopoiskapp.domain.Film
import com.evanstukalov.fakekinopoiskapp.domain.Genre
import com.evanstukalov.fakekinopoiskapp.network.RetrofitInstance
import com.evanstukalov.fakekinopoiskapp.utils.asDatabaseModel
import com.evanstukalov.fakekinopoiskapp.utils.asDomainModel
import com.evanstukalov.fakekinopoiskapp.utils.asGenreDatabase
import com.evanstukalov.fakekinopoiskapp.utils.asGenreModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val database: FilmDataBase) {

    suspend fun refreshFilms(){
        withContext(Dispatchers.IO){
            val listResult = RetrofitInstance.api.getFilms()
            database.filmDao.insertFilms(listResult.asDatabaseModel())
            database.filmDao.insertGenres(listResult.asGenreDatabase())
        }
    }

    val films: LiveData<List<Film>> = Transformations.map(database.filmDao.getFilms()){
        it.asDomainModel()
    }

    val genres: LiveData<List<Genre>> = Transformations.map(database.filmDao.getGenres()){
        it.asGenreModel()
    }

}