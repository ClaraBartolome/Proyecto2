package com.example.proyecto2.Usercases

import com.example.proyecto2.data.MovieRepository
import com.example.proyecto2.data.models.MovieDetails
import kotlinx.coroutines.flow.Flow

typealias GetMovieDetailsUseBaseCase = BaseUseCase<String, Flow<MovieDetails>>

class GetMovieDetailsUseCase(
    private val repository: MovieRepository
) : GetMovieDetailsUseBaseCase {
    override suspend fun invoke(id: String) = repository.getMovieDetails(id)
}