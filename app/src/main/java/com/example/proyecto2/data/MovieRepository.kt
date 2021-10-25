package com.example.proyecto2.data

import com.example.proyecto2.core.Common.API_KEY
import com.example.proyecto2.data.models.MovieBasicResponse
import com.example.proyecto2.data.models.MovieDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepository (private val movieAPI: MovieAPI) {
    fun getMovies(): Flow <MovieBasicResponse> =
        flow { emit(movieAPI.getMovieList()) }

    fun getMovieSearch(s: String): Flow <MovieBasicResponse> =
        flow { emit(movieAPI.getMovieSearch(API_KEY, s)) }

    fun getMovieDetails(s: String): Flow <MovieDetails> =
        flow { emit(movieAPI.getMovieDetails(s, API_KEY)) }
}