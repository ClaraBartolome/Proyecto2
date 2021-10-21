package com.example.proyecto2.data

import com.example.proyecto2.R
import com.example.proyecto2.core.Common.BASEIMG_URL
import com.example.proyecto2.core.Common.POSTER_PATH
import kotlin.contracts.ReturnsNotNull

class Database {
    fun loadFilms(): List<Film>{
        return listOf<Film>(
            Film(R.string.Title_film, BASEIMG_URL + POSTER_PATH),
            Film(R.string.Title_film, BASEIMG_URL + POSTER_PATH),
            Film(R.string.Title_film, BASEIMG_URL + POSTER_PATH),
            Film(R.string.Title_film, BASEIMG_URL + POSTER_PATH),
            Film(R.string.Title_film, BASEIMG_URL + POSTER_PATH),
            Film(R.string.Title_film, BASEIMG_URL + POSTER_PATH),
            Film(R.string.Title_film, BASEIMG_URL + POSTER_PATH),
            Film(R.string.Title_film, BASEIMG_URL + POSTER_PATH),
            Film(R.string.Title_film, BASEIMG_URL + POSTER_PATH),
            Film(R.string.Title_film, BASEIMG_URL + POSTER_PATH),
            Film(R.string.Title_film, BASEIMG_URL + POSTER_PATH),
        )
    }
}

data class Film(val titleId: Int, val image_URL: String)