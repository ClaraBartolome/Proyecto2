package com.example.proyecto2.Usercases

import com.example.proyecto2.data.MovieRepository
import com.example.proyecto2.data.models.MovieBasic
import com.example.proyecto2.data.models.MovieBasicResponse
import kotlinx.coroutines.flow.Flow

typealias GetMoviesUseBaseCase = BaseUseCase<Unit, Flow<MovieBasicResponse>>

class GetMoviesUseCase (private val repository: MovieRepository): GetMoviesUseBaseCase {
    override suspend fun invoke(params: Unit) = repository.getMovies()
}