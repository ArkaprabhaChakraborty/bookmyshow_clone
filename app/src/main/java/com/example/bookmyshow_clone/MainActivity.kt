package com.example.bookmyshow_clone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchMovies()
    }

    private fun fetchMovies() {
        val internetconnection = NetworkHelper(this)
        if (internetconnection.isNetworkConnected())
        {

        }
        else
        {

        }
    }

    private fun showMovies(movies: ArrayList<Movie>)
    {
        recyclerView.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        recyclerView.setHasFixedSize(false)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = MoviesAdapter(movies)

    }
}