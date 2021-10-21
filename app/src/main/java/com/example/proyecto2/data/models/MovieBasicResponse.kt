package com.example.proyecto2.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieBasicResponse(
    @SerializedName("page") val id: Int = 0){
    //@SerializedName("results") val movieList: List<MovieBasic> = emptyList()) {
}