package com.evanstukalov.fakekinopoiskapp.ui.list

import android.app.Application
import android.widget.ListView
import androidx.lifecycle.*
import com.evanstukalov.fakekinopoiskapp.database.FilmDataBase
import com.evanstukalov.fakekinopoiskapp.domain.Film
import com.evanstukalov.fakekinopoiskapp.domain.Genre
import com.evanstukalov.fakekinopoiskapp.repository.Repository
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException
import java.lang.Exception
import java.util.*

class ListViewModel(application: Application, database: FilmDataBase): AndroidViewModel(application) {

    private val repository = Repository(database)

    val films = repository.films

    val genres = Transformations.map(repository.genres){ genres ->

        val uniqueSet = mutableSetOf<String>()

        for (collection in genres){
            for (item in collection.genres){
                if (item != ""){
                    uniqueSet.add(item.capitalize(Locale.ROOT))
                }
            }
        }
        Timber.d("${uniqueSet.toList()}")
        uniqueSet.toList()
    }



    /**
     * init{} is called immediately when this ViewModel is created.
     */
    init {
        refreshDataFromRepository()
    }

    /**
     * Event triggered for network error.
     */
    private var _eventNetworkError = MutableLiveData(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError



    /**
     * Flag to display the error message.
     */
    private var _isNetworkErrorShown = MutableLiveData(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    /**
     * Refresh data from the repository. Use a coroutine launch to run in a
     * background thread.
     */
    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                repository.refreshFilms()
                _eventNetworkError.value = false

            } catch (e: Exception) {
                if(films.value.isNullOrEmpty()) {
                    _eventNetworkError.value = true
                }
            }
        }
    }

    /**
     * Resets the network error flag.
     */
    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    private val _navigateToSelectedFilm = MutableLiveData<Film>()
    val navigateToSelectedFilm: LiveData<Film>
        get() = _navigateToSelectedFilm

    fun displayFilmDetails(marsProperty: Film) {
        _navigateToSelectedFilm.value = marsProperty
    }

    fun displayPropertyDetailesCompleted(){
        _navigateToSelectedFilm.value = null
    }


    /**
     * Factory for constructing ListViewModel with parameter
     */
    class Factory(val app: Application, val database: FilmDataBase) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ListViewModel(app, database) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}