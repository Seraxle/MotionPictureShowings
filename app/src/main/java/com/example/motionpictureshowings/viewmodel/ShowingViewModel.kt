package com.example.motionpictureshowings.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.motionpictureshowings.model.MovieResults
import com.example.motionpictureshowings.model.TvResults

class ShowingViewModel: ViewModel() {

    private var movieLiveList: MutableLiveData<List<MovieResults>> = MutableLiveData()
    private var tvLiveList: MutableLiveData<List<TvResults>> = MutableLiveData()
    private var movieShowingItemList: List<MovieResults>? = ArrayList()
    private var tvShowingItemList: List<TvResults>? = ArrayList()
    private var isMovie: Boolean = false
    private var isTvShow: Boolean = false
    private var chosenMovie: MovieResults? = null
    private var chosenTvShow: TvResults? = null

    fun getMovieShowingItemList() : MutableLiveData<List<MovieResults>> {
        return this.movieLiveList
    }

    fun setMovieShowingItemList(movieShowingItemList: List<MovieResults>) {
        movieLiveList.value = movieShowingItemList
        //this.movieShowingItemList = movieShowingItemList
        this.isMovie = false
    }

    fun getTvShowingItemList() : MutableLiveData<List<TvResults>> {
        return tvLiveList
    }

    fun setTvShowingItemList(tvShowingItemList: List<TvResults>) {
        tvLiveList.value = tvShowingItemList
        //this.tvShowingItemList = tvShowingItemList
        this.isTvShow = false
    }

    fun getChosenMovie() : MovieResults? {
        return this.chosenMovie
    }

    fun setChosenMovie(chosenMovie: MovieResults?) {
        this.chosenMovie = chosenMovie
        this.isMovie = true
    }

    fun getChosenTvShow() : TvResults? {
        return this.chosenTvShow
    }

    fun setChosenTvShow(chosenTvShow: TvResults?) {
        this.chosenTvShow = chosenTvShow
        this.isTvShow = true
    }

    fun isMovieResults() : Boolean {
        return this.isMovie
    }

    fun isTvShowResults() : Boolean {
        return this.isTvShow
    }

    fun destroy() {
        this.movieShowingItemList = null
        this.tvShowingItemList = null
        this.chosenMovie = null
        this.chosenTvShow = null
    }
}