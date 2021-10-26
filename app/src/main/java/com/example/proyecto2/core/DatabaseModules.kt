package com.example.proyecto2.core

import androidx.room.Room
import com.example.proyecto2.core.Common.DB_NAME
import com.example.proyecto2.core.Common.DB_PATH
import com.example.proyecto2.data.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module

val databaseModule: Module = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            DB_NAME
        )
            .createFromAsset(DB_PATH + DB_NAME)
            .allowMainThreadQueries()
            .build()
    }

    single { get<AppDatabase>().movieDao() }

}

