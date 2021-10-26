package com.example.proyecto2.presentation.ShowMovieSearchScreen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import com.example.proyecto2.R
import com.example.proyecto2.core.Common.viewBinding
import com.example.proyecto2.data.AppDatabase
import com.example.proyecto2.databinding.FragmentShowMovieSearchBinding
import com.example.proyecto2.presentation.StartScreen.MovieListAdapter
import com.example.proyecto2.presentation.models.LoadingState
import org.koin.androidx.viewmodel.ext.android.viewModel


class showMovieFragment : Fragment(R.layout.fragment_show_movie_search) {

    val viewModel by viewModel<ShowMovieSearchViewModel>()
    val binding by viewBinding<FragmentShowMovieSearchBinding>()

    val madapter: ShowMovieSearchAdapter by lazy {
        ShowMovieSearchAdapter(this.requireContext(), AppDatabase.getDatabase(this.requireContext()))
    }

    companion object {
        val TITLE = "title"
    }

    private lateinit var titleId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            titleId = it.getString(TITLE).toString()

            Log.d("Title:", titleId)
            titleId = titleId.replace(" ", "+")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        attachObservers()
        setupAdapter()
        viewModel.getMovieSearch(titleId)
    }

    private fun setupAdapter() {
        with(binding) {
            recyclerView.adapter = madapter
        }
    }

    private fun attachObservers() {
        viewModel.movieSearch.observe(viewLifecycleOwner, {
            madapter.setDatabase(it)
            madapter.notifyDataSetChanged()
        })

        viewModel.loadingState.observe(viewLifecycleOwner, { loadingState ->
            when (loadingState.status) {
                LoadingState.Status.RUNNING -> showLoading(true)
                LoadingState.Status.SUCCESS -> showLoading(false)
                LoadingState.Status.FAILED -> showError()
            }
        })
    }

    private fun showError() {
        with(binding) {
            progressBar.isVisible = false
            recyclerView.isVisible = false
            errorText.isVisible = true
            errorText.text = "Load error. Retry again.  Maybe the title isn't right"
            retryButton.setOnClickListener { viewModel.getMovieSearch(titleId) }
            retryButton.isVisible = true
        }
    }

    private fun showLoading(b: Boolean) {
        with(binding) {
            progressBar.isVisible = b
            recyclerView.isVisible = !b
            errorText.isVisible = false
            retryButton.isVisible = false
        }
    }


}