package com.example.proyecto2.Usercases

import com.example.proyecto2.data.MovieRepository
import com.example.proyecto2.data.models.MovieBasicResponse
import kotlinx.coroutines.flow.Flow

typealias GetMovieSearchUseBaseCase = BaseUseCase<String, Flow<MovieBasicResponse>>

class GetMovieSearchUseCase(
    private val repository: MovieRepository
) : GetMovieSearchUseBaseCase {
    override suspend fun invoke(name: String) = repository.getMovieSearch(name)
}