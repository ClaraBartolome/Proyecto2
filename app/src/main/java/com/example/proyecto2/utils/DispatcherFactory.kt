package com.example.proyecto2.utils

import kotlin.coroutines.CoroutineContext

interface DispatcherFactory {
    fun getMain(): CoroutineContext
    fun getIO(): CoroutineContext
}