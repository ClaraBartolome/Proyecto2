package com.example.proyecto2.presentation.StartScreen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.proyecto2.models.MainViewModel
import com.example.proyecto2.core.Common.viewBinding
import com.example.proyecto2.R
import com.example.proyecto2.data.Database
import com.example.proyecto2.databinding.FragmentStartScreenBinding
import com.example.proyecto2.presentation.models.LoadingState
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.log


class StartScreenFragment : Fragment(R.layout.fragment_start_screen) {

    val viewModel by viewModel<StartScrenViewModel>()

    /*lateinit var _binding: FragmentStartScreenBinding
    private val binding get() = _binding!! */
    val binding by viewBinding<FragmentStartScreenBinding>()

    val madapter: MovieListAdapter by lazy {
        MovieListAdapter(this.requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        attachObservers()
        setupAdapter()
        viewModel.getMovies()
    }

    fun setupAdapter() {
        with(binding) {
            recyclerView.adapter = madapter
        }
    }

    fun attachObservers() {
        viewModel.movieList.observe(viewLifecycleOwner, {
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
            searchButton.isVisible = false
            textInputLayout.isVisible = false
            errorText.isVisible = true
            errorText.text = "Load error. Retry again."
            retryButton.setOnClickListener { viewModel.getMovies() }
            retryButton.isVisible = true
        }
    }

    private fun showLoading(b: Boolean) {
        with(binding) {
            progressBar.isVisible = b
            recyclerView.isVisible = !b
            errorText.isVisible = false
            retryButton.isVisible = false
            searchButton.isVisible = !b
            textInputLayout.isVisible = !b

            if (!b) {
                searchButton.setOnClickListener {
                    textInputLayout.editText?.let {
                        searchFilm(it.text.toString())
                    }
                }
            }
        }
    }

    private fun searchFilm(name: String) {
        val action =
            StartScreenFragmentDirections
                .actionStartScreenFragmentToShowMovieFragment(
                    title = name
                )
        findNavController()
            .navigate(action)

    }

}