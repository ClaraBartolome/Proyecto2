package com.example.proyecto2.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieBasic(@Expose @SerializedName("title") val title: String,
                      @Expose @SerializedName("id") val id: String,
                      @Expose @SerializedName("poster_path") val img_url: String
) {
}