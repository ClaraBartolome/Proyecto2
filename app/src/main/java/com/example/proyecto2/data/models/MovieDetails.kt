package com.example.proyecto2.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class MovieDetails(
    @Expose @SerializedName("title") val title: String,
    @Expose @SerializedName("poster_path") val img_url: String,
    @Expose @SerializedName("overview") val desc: String,
    @Expose @SerializedName("vote_average") val score: Double,
) {
}