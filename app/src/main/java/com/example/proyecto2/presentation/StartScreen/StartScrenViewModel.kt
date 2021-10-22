package com.example.proyecto2.presentation.StartScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.proyecto2.Usercases.GetMoviesUseCase
import com.example.proyecto2.data.models.MovieBasic
import com.example.proyecto2.data.models.MovieBasicResponse
import com.example.proyecto2.presentation.BaseViewModel
import com.example.proyecto2.presentation.models.LoadingState
import com.example.proyecto2.utils.DispatcherFactory
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StartScrenViewModel(
    private val dispatcherFactory: DispatcherFactory,
    private val getMoviesUseCase: GetMoviesUseCase
) : BaseViewModel(dispatcherFactory) {

    companion object {
        const val LOAD_ERROR = "load error"
    }

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState
    private val _movielist = MutableLiveData<MovieBasicResponse>()
    val movieList: LiveData<MovieBasicResponse>
        get() = _movielist

    fun getMovies() {
        launch {
            withContext(dispatcherFactory.getIO()) {
                getMoviesUseCase(Unit).onStart { _loadingState.postValue(LoadingState.LOADING) }
                    .catch {
                        _loadingState.postValue(
                            LoadingState.error(LOAD_ERROR)
                        )
                        Log.d("FAIL:", "Fallo")

                    }
                    .collect {
                        _movielist.postValue(it)
                        _loadingState.postValue(LoadingState.LOADED)
                        Log.d("LOADED:", "${it.movieList.size}")
                    }
            }
        }
    }

}


