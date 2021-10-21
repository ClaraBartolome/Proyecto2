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
import com.example.proyecto2.data.Film

class MovieListAdapter(private val context: Context) :
    RecyclerView.Adapter<MovieListAdapter.ItemViewHolder>() {

    var dataset : List<Film> = emptyList()

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
        val item = dataset[position]
        holder.title.text = context.resources.getString(item.titleId)


        Glide.with(context)
            .load(item.image_URL)
            .fitCenter()
            .placeholder(R.drawable.image_placeholder)
            .into(holder.poster)

    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    fun setDatabase( data: List<Film>){
        dataset = data
    }

}