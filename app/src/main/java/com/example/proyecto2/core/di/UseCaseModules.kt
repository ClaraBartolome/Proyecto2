package com.example.proyecto2.core.di

import com.example.proyecto2.Usercases.*
import com.example.proyecto2.data.MovieRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val useCasesModule = module {
    single(named("get_movies")) { provideGetMovies(get()) }
    single(named("get_movie_search")) {provideGetMovieSearch(get()) }
    single(named("get_movie_details")) { provideGetMovieDetails(get()) }

}

fun provideGetMovies(repository: MovieRepository): GetMoviesUseBaseCase {
    return GetMoviesUseCase(repository)
}

fun provideGetMovieSearch(repository: MovieRepository): GetMovieSearchUseBaseCase {
    return GetMovieSearchUseCase(repository)
}

fun provideGetMovieDetails(repository: MovieRepository): GetMovieDetailsUseBaseCase {
    return GetMovieDetailsUseCase(repository)
}