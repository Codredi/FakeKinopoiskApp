package com.evanstukalov.fakekinopoiskapp.utils

import com.evanstukalov.fakekinopoiskapp.database.DatabaseFilm
import com.evanstukalov.fakekinopoiskapp.database.DatabaseGenre
import com.evanstukalov.fakekinopoiskapp.domain.Film
import com.evanstukalov.fakekinopoiskapp.domain.Genre
import com.evanstukalov.fakekinopoiskapp.network.NetworkFilmsContainer

/**
 * Convert Network results to database objects
 */
fun NetworkFilmsContainer.asDatabaseModel(): List<DatabaseFilm> {
    return films.map {
        DatabaseFilm(
            description = it.description,
            genres = it.genres,
            name = it.name,
            id = it.id,
            imageUrl = it.imageUrl,
            localizedName = it.localizedName,
            rating = it.rating,
            year = it.year
        )
    }
}

fun NetworkFilmsContainer.asGenreDatabase(): List<DatabaseGenre> {
    return films.map {
        DatabaseGenre(
                genres = it.genres,
        )
    }
}

/**
 * Map DatabaseVideos to domain entities
 */
fun List<DatabaseFilm>.asDomainModel(): List<Film> {
    return map {
        Film(
            description = it.description,
            genres = it.genres,
            name = it.name,
            id = it.id,
            imageUrl = it.imageUrl,
            localizedName = it.localizedName,
            rating = it.rating,
            year = it.year
        )
    }
}

fun List<DatabaseGenre>.asGenreModel(): List<Genre> {
    return map {
        Genre(
                genres = it.genres,
        )
    }
}