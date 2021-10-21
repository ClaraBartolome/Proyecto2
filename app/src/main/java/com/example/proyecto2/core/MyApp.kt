package com.example.proyecto2.core

import android.app.Application
import com.example.proyecto2.*
import com.example.proyecto2.core.di.useCasesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(
                listOf(
                    appModule,
                    dispatcherFactoryModule,
                    netModule,
                    viewModelModule,
                    useCasesModule,
                    movieAPIModule,
                    repositoryModule
                )
            )
        }
    }
}