package com.example.proyecto2.presentation.ShowMovieSearchScreen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyecto2.R
import com.example.proyecto2.core.Common.BASEIMG_URL
import com.example.proyecto2.data.models.MovieBasicResponse
import com.example.proyecto2.presentation.StartScreen.MovieListAdapter
import com.example.proyecto2.presentation.StartScreen.StartScreenFragmentDirections

class ShowMovieSearchAdapter(private val context: Context) :
    RecyclerView.Adapter<ShowMovieSearchAdapter.ItemViewHolder>() {

    var dataset: MovieBasicResponse = MovieBasicResponse()

    class ItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title_text)
        val poster: ImageView = view.findViewById(R.id.poster_image)
        val container: CardView = view.findViewById(R.id.card_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_list, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset.movieList[position]
        holder.title.text = item.title


        Glide.with(context)
            .load(BASEIMG_URL + item.img_url)
            .fitCenter()
            .into(holder.poster)

        holder.container.setOnClickListener {
            val action =
                showMovieFragmentDirections
                    .actionShowMovieFragmentToShowMovieInfoFragment(
                        id = item.id)
            holder.view.findNavController().navigate(action)
        }


    }

    override fun getItemCount(): Int {
        return dataset.movieList.size
    }

    fun setDatabase(data: MovieBasicResponse) {
        dataset = data
    }

}