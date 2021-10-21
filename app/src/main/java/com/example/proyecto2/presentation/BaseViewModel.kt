package com.example.proyecto2.presentation

import androidx.lifecycle.ViewModel
import com.example.proyecto2.utils.DispatcherFactory
import org.koin.core.KoinComponent
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

abstract class BaseViewModel(
    private val dispatcherFactory: DispatcherFactory
) : ViewModel(), CoroutineScope, KoinComponent {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = dispatcherFactory.getMain() + job

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}