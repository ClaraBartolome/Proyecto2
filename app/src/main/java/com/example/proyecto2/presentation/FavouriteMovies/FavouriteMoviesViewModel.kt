package com.example.proyecto2.presentation.FavouriteMovies

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proyecto2.data.AppDatabase
import com.example.proyecto2.data.models.MovieTable
import com.example.proyecto2.presentation.models.LoadingState

class FavouriteMoviesViewModel(): ViewModel() {

    companion object {
        const val LOAD_ERROR = "load error"
    }

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState
    private val _movieList = MutableLiveData<List<MovieTable>>()
    val movieList: LiveData<List<MovieTable>>
        get() = _movieList


    fun getMovieList(context: Context){
        _loadingState.postValue(LoadingState.LOADING)
        val db = AppDatabase.getDatabase(context)
        _movieList.postValue(db.movieDao().getAll())
        _loadingState.postValue(LoadingState.LOADED)
    }



}