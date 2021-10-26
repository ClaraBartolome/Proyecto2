package com.example.proyecto2.presentation.ShowMovieSearchScreen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyecto2.R
import com.example.proyecto2.core.Common.BASEIMG_URL
import com.example.proyecto2.data.AppDatabase
import com.example.proyecto2.data.models.MovieBasicResponse
import com.example.proyecto2.data.models.MovieTable

class ShowMovieSearchAdapter(private val context: Context, private val db: AppDatabase) :
    RecyclerView.Adapter<ShowMovieSearchAdapter.ItemViewHolder>() {

    var dataset: MovieBasicResponse = MovieBasicResponse()
    val hearts = listOf(R.drawable.ic_favorite, R.drawable.ic_favourite_border)

    class ItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title_text)
        val poster: ImageView = view.findViewById(R.id.poster_image)
        val container: CardView = view.findViewById(R.id.card_view)
        val likeButton: ImageButton = view.findViewById(R.id.button_like)
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

        if(db.movieDao().isRowIsExist(item.id.toInt())){
            holder.likeButton.setImageResource(hearts[0])
        }else{
            holder.likeButton.setImageResource(hearts[1])
        }

        holder.likeButton.setOnClickListener {
            if(db.movieDao().isRowIsExist(item.id.toInt())){
                //Si existe eliminamos la película de la tabla
                db.movieDao().deleteMovie(MovieTable(item.id.toInt(), item.title, item.img_url))
                holder.likeButton.setImageResource(hearts[1])
            }else{
                //Si no existe la añadimos
                db.movieDao().insertMovie(MovieTable(item.id.toInt(), item.title, item.img_url))
                holder.likeButton.setImageResource(hearts[0])
            }
        }


    }

    override fun getItemCount(): Int {
        return dataset.movieList.size
    }

    fun setDatabase(data: MovieBasicResponse) {
        dataset = data
    }

}