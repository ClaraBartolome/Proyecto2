package com.example.proyecto2.presentation.FavouriteMovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import com.example.proyecto2.R
import com.example.proyecto2.core.Common.viewBinding
import com.example.proyecto2.data.AppDatabase
import com.example.proyecto2.databinding.FragmentFavouriteMoviesBinding
import com.example.proyecto2.databinding.FragmentShowMovieInfoBinding
import com.example.proyecto2.presentation.ShowMovieSearchScreen.ShowMovieSearchAdapter
import com.example.proyecto2.presentation.models.LoadingState
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouriteMoviesFragment : Fragment(R.layout.fragment_favourite_movies) {

    val viewModel by viewModel<FavouriteMoviesViewModel>()
    val binding by viewBinding<FragmentFavouriteMoviesBinding>()

    val madapter: FavouriteMoviesAdapter by lazy {
        FavouriteMoviesAdapter(this.requireContext(), AppDatabase.getDatabase(this.requireContext()))
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovieList(view.context)
        attachObservers()
        setupAdapter()
    }

    private fun setupAdapter() {
        with(binding) {
            recyclerView.adapter = madapter
        }
    }

    private fun attachObservers() {
        viewModel.movieList.observe(viewLifecycleOwner, {
            madapter.setDatabase(it)
            madapter.notifyDataSetChanged()
        })

        viewModel.loadingState.observe(viewLifecycleOwner, {loadingState ->
            when (loadingState.status) {
                LoadingState.Status.RUNNING -> showLoading(true)
                LoadingState.Status.SUCCESS -> showLoading(false)
                LoadingState.Status.FAILED -> showError()
            }
        })
    }

    private fun showError() {
        TODO("Not yet implemented")
    }

    private fun showLoading(b: Boolean) {
        with(binding){
            recyclerView.isVisible = !b
        }
    }


}