package com.example.proyecto2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.proyecto2.core.Common.DB_PATH
import com.example.proyecto2.core.Common.DB_NAME
import com.example.proyecto2.data.models.MovieTable

@Database(entities = arrayOf(MovieTable::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DB_NAME
                )
                    .createFromAsset(DB_PATH + DB_NAME)
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance

                instance
            }
        }

    }
}