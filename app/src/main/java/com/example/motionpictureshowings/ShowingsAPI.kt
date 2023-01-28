package com.example.motionpictureshowings

import com.example.motionpictureshowings.model.MovieItem
import com.example.motionpictureshowings.model.TvItem
import retrofit2.Call
import retrofit2.http.GET

interface ShowingsAPI {

    @GET("movie/now_playing?api_key=24c8ce69d6d56660a4b36f59fd8ed31c&language=en-US&page=1")
    fun getMovieDetails(): Call<ArrayList<MovieItem>>

    @GET("https://api.themoviedb.org/3/tv/on_the_air?api_key=24c8ce69d6d56660a4b36f59fd8ed31c&language=en-US&page=1")
    fun getTvDetails(): Call<ArrayList<TvItem>>
}