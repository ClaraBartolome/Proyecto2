package com.example.proyecto2.presentation.StartScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.proyecto2.models.MainViewModel
import com.example.proyecto2.core.Common.viewBinding
import com.example.proyecto2.R
import com.example.proyecto2.data.Database
import com.example.proyecto2.databinding.FragmentStartScreenBinding
import org.koin.android.ext.android.inject


class StartScreenFragment : Fragment(R.layout.fragment_start_screen) {

    val viewModel: MainViewModel by inject<MainViewModel>()
    /*lateinit var _binding: FragmentStartScreenBinding
    private val binding get() = _binding!! */
    val binding by viewBinding<FragmentStartScreenBinding>()

    val madapter: MovieListAdapter by lazy {
        MovieListAdapter(this.requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        test()
    }
    fun test(){
        //binding.textFragment.text = "Holiwis"
        madapter.setDatabase(Database().loadFilms())
        with(binding){
            recyclerView.adapter = madapter
        }
    }

}