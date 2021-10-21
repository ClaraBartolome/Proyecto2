package com.example.proyecto2.data

import com.example.proyecto2.core.Common.API_KEY
import com.example.proyecto2.core.Common.LENGUAGE_ESP
import com.example.proyecto2.core.Common.SORT_POPULARITY
import com.example.proyecto2.data.models.MovieBasic
import com.example.proyecto2.data.models.MovieBasicResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call

interface MovieAPI {
    //todo pagination
    @GET("discover/movie?api_key={api_key}&lenguage={lenguage}&sort_by={sort}&include_adult={includeAdult}")
    suspend fun getMovieList(
        @Path("api_key") api_key: String = API_KEY,
        @Path("lenguage") idioma: String = LENGUAGE_ESP,
        @Path("sort") sort: String = SORT_POPULARITY,
        @Path("includeAdult") adult: String = "false"
    ): MovieBasicResponse
}

//https://api.themoviedb.org/3/discover/movie?api_key=da2259313c5f0cc00e758f9c322dc985&lenguage=es-ES&sort_by=popularity.desc&include_adult=false
