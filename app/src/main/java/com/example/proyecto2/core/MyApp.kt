package com.example.proyecto2.core

import android.app.Application
import com.example.proyecto2.appModule
import com.example.proyecto2.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            androidLogger()
            modules(listOf(appModule, viewModelModule))
        }
    }
}