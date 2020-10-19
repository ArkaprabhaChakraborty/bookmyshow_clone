package com.example.bookmyshow_clone

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService
{
    @GET("discover/movie")
    fun getmovies(@Query("api_key")key:String):Call<MovieResponse>
}