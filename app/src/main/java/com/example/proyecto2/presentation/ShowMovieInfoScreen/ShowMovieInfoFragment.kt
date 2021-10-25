package com.example.proyecto2.presentation.ShowMovieInfoScreen

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.proyecto2.R
import com.example.proyecto2.core.Common.BASEIMG_URL
import com.example.proyecto2.core.Common.viewBinding
import com.example.proyecto2.data.models.MovieDetails
import com.example.proyecto2.databinding.FragmentShowMovieInfoBinding
import com.example.proyecto2.databinding.FragmentShowMovieSearchBinding
import com.example.proyecto2.presentation.ShowMovieSearchScreen.ShowMovieSearchViewModel
import com.example.proyecto2.presentation.models.LoadingState
import org.koin.androidx.viewmodel.ext.android.viewModel


class ShowMovieInfoFragment : Fragment(R.layout.fragment_show_movie_info) {

    val viewModel by viewModel<ShowMovieInfoViewModel>()
    val binding by viewBinding<FragmentShowMovieInfoBinding>()

    companion object {
        val ID = "id"
    }

    private lateinit var movie_id:String
    private lateinit var movieinfo: MovieDetails

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movie_id = it.getString(ID).toString()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        attachObservers()
        viewModel.getMovieInfo(movie_id)
    }

    private fun attachObservers() {
        viewModel.movieInfo.observe(viewLifecycleOwner, {
            movieinfo = it
            attachInfo()
        })

        viewModel.loadingState.observe(viewLifecycleOwner, { loadingState ->
            when (loadingState.status) {
                LoadingState.Status.RUNNING -> showLoading(true)
                LoadingState.Status.SUCCESS -> showLoading(false)
                LoadingState.Status.FAILED -> showError()
            }
        })
    }

    private fun attachInfo() {
        with(binding){
            infoTitle.text = movieinfo.title
            infoOverview.text = movieinfo.desc
            infoScore.text = movieinfo.score.toString()

            Glide.with(root)
                .load(BASEIMG_URL + movieinfo.img_url)
                .fitCenter()
                .into(infoPosterImageView)
        }
    }

    private fun showError() {
        with(binding){
            progressBar.isVisible = false
            infoTitle.isVisible = false
            infoOverview.isVisible = false
            infoScore.isVisible = false
            infoPosterImageView.isVisible = false
            errorText.isVisible = true
            retryButton.setOnClickListener { viewModel.getMovieInfo(movie_id) }
            retryButton.isVisible = true
        }
    }

    private fun showLoading(b: Boolean) {
        with(binding){
            progressBar.isVisible = b
            errorText.isVisible = false
            retryButton.isVisible = false
            infoTitle.isVisible = !b
            infoOverview.isVisible = !b
            infoScore.isVisible = !b
            infoPosterImageView.isVisible = !b
        }
    }


}