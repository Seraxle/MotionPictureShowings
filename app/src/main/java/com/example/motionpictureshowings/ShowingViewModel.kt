package com.example.motionpictureshowings

import androidx.lifecycle.ViewModel
import com.example.motionpictureshowings.model.MovieItem
import com.example.motionpictureshowings.model.TvItem

class ShowingViewModel: ViewModel() {

    private var movieShowingItemList: ArrayList<MovieItem> = ArrayList()
    private var tvShowingItemList: ArrayList<TvItem> = ArrayList()

    fun getMovieShowingItemList() : ArrayList<MovieItem> {
        return this.movieShowingItemList
    }

    fun setMovieShowingItemList(movieShowingItemList: ArrayList<MovieItem>) {
        this.movieShowingItemList = movieShowingItemList
    }

    fun getTvShowingItemList() : ArrayList<TvItem> {
        return this.tvShowingItemList
    }

    fun setTvShowingItemList(tvShowingItemList: ArrayList<TvItem>) {
        this.tvShowingItemList = tvShowingItemList
    }
}