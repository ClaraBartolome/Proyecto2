package com.example.proyecto2.presentation.ShowMovieInfoScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.proyecto2.Usercases.GetMovieDetailsUseCase
import com.example.proyecto2.data.models.MovieDetails
import com.example.proyecto2.presentation.BaseViewModel
import com.example.proyecto2.presentation.models.LoadingState
import com.example.proyecto2.utils.DispatcherFactory
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShowMovieInfoViewModel (val dispatcherFactory: DispatcherFactory,
                              private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : BaseViewModel(dispatcherFactory) {

    companion object {
        const val LOAD_ERROR = "load error"
    }

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState
    private val _movieInfo = MutableLiveData<MovieDetails>()
    val movieInfo: LiveData<MovieDetails>
        get() = _movieInfo

    fun getMovieInfo(id:String) {
        launch {
            withContext(dispatcherFactory.getIO()) {
                getMovieDetailsUseCase(id).onStart { _loadingState.postValue(LoadingState.LOADING) }
                    .catch {
                        _loadingState.postValue(
                            LoadingState.error(LOAD_ERROR)
                        )
                        Log.d("FAIL:", "Fallo")

                    }
                    .collect {
                        _movieInfo.postValue(it)
                        _loadingState.postValue(LoadingState.LOADED)
                        Log.d("LOADED:", "${it.title}")
                    }
            }
        }
    }

}