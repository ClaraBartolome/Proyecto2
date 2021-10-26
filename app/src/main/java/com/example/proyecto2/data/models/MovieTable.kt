package com.example.proyecto2.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MovieTable")
data class MovieTable(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "Title") val title: String?,
    @ColumnInfo(name = "Poster_path") val url: String?
)