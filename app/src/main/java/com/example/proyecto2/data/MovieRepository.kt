package com.example.proyecto2.data

import com.example.proyecto2.core.Common.API_KEY
import com.example.proyecto2.core.Common.LENGUAGE_ESP
import com.example.proyecto2.core.Common.SORT_POPULARITY
import com.example.proyecto2.data.models.MovieBasic
import com.example.proyecto2.data.models.MovieBasicResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepository (private val movieAPI: MovieAPI) {
    fun getMovies(): Flow <MovieBasicResponse> =
        flow { emit(movieAPI.getMovieList()) }
}