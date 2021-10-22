package com.example.proyecto2.presentation.showMovieScreen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.proyecto2.R


class showMovieFragment : Fragment() {

    companion object {
        val TITLE = "title"
    }

    private lateinit var titleId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            titleId = it.getString(TITLE).toString()

            Log.d("Title:", titleId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_movie, container, false)
    }

}