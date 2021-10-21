package com.example.proyecto2.core.di

import com.example.proyecto2.Usercases.GetMoviesUseCase
import com.example.proyecto2.Usercases.GetMoviesUseBaseCase
import com.example.proyecto2.data.MovieRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val useCasesModule = module {
    single(named("get_movies")) { provideGetMovies(get()) }
}

fun provideGetMovies(repository: MovieRepository): GetMoviesUseBaseCase {
    return GetMoviesUseCase(repository)
}