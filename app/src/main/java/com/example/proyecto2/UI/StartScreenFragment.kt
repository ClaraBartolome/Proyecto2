package com.example.proyecto2.UI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.proyecto2.Models.MainViewModel
import com.example.proyecto2.R
import com.example.proyecto2.databinding.FragmentStartScreenBinding
import org.koin.android.ext.android.inject


class StartScreenFragment : Fragment() {

    val viewModel: MainViewModel by inject<MainViewModel>()
    lateinit var _binding: FragmentStartScreenBinding
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartScreenBinding.inflate(layoutInflater, container, false)
        var view: View
        with(binding){
            view = root
            textFragment.text = viewModel.data
            Log.d("DATA", viewModel.data)
        }
        return view
    }

}