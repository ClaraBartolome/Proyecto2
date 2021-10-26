package com.example.proyecto2.data

import androidx.room.*
import com.example.proyecto2.data.models.MovieTable
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM MovieTable")
    fun getAll(): List<MovieTable>

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    fun insertMovie(vararg  movie: MovieTable)

    @Delete
    fun deleteMovie(movie: MovieTable)

    @Query("SELECT EXISTS(SELECT * FROM MovieTable WHERE id = :id)")
    fun isRowIsExist(id : Int) : Boolean

}