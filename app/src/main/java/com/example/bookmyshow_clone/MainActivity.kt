package com.example.bookmyshow_clone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.bookmyshow_clone.R.string.error_msg
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchMovies()
    }
    companion object {
        private const val API_KEY = "7bc0651fe0ea5973822df3bd27e779d9"
    }
    private fun fetchMovies() {
        showProgress()
        val internetconnection = NetworkHelper(this)
        if (internetconnection.isNetworkConnected())
        {
            val request = RetrofitBuilder.buildService()
            val call:Call<MovieResponse> = request.getmovies(API_KEY)
            call.enqueue(object:Callback<MovieResponse>{
                override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>)
                {
                    hideProgress()
                    if(response.isSuccessful && response.body() != null)
                    {
                        val movieResponse:MovieResponse = response.body()!!
                        val movies:List<Movie> = movieResponse.results
                        showMovies(movies as ArrayList<Movie>)
                    }
                    else
                    {
                        hideProgress()
                        showErrorMessage(resources.getString(error_msg))
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    showErrorMessage(t.message)
                }
            })
        }
        else
        {
            showErrorMessage(resources.getString(R.string.nointernet))
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
    private fun showProgress()
    {
        progressBar.visibility = View.VISIBLE
    }
    private fun hideProgress()
    {
        progressBar.visibility = View.GONE
    }
    private fun showErrorMessage(error:String?)
    {
        errorView.visibility = View.VISIBLE
        errorView.text = error
    }
}