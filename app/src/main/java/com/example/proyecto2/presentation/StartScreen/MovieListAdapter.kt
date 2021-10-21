package com.example.proyecto2.presentation.StartScreen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyecto2.R
import com.example.proyecto2.core.Common.BASEIMG_URL
import com.example.proyecto2.data.Film
import com.example.proyecto2.data.models.MovieBasic
import com.example.proyecto2.data.models.MovieBasicResponse

class MovieListAdapter(private val context: Context) :
    RecyclerView.Adapter<MovieListAdapter.ItemViewHolder>() {

    var dataset : MovieBasicResponse = MovieBasicResponse()

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title_text)
        val poster: ImageView = view.findViewById(R.id.poster_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_list, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
      /*  val item = dataset.movieList[position]
        holder.title.text = item.title


        Glide.with(context)
            .load(BASEIMG_URL + item.img_url)
            .fitCenter()
            .placeholder(R.drawable.image_placeholder)
            .into(holder.poster)
    */
    }

    override fun getItemCount(): Int {
        return 0 //dataset.movieList.size
    }

    fun setDatabase( data: MovieBasicResponse){
        dataset = data
    }

}