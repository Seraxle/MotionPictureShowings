package com.example.motionpictureshowings.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.motionpictureshowings.R
import com.example.motionpictureshowings.model.MovieResults
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieRecyclerAdapter(
    private val recyclerList: List<MovieResults>?,
    private val listener: onMovieClickListener
) : RecyclerView.Adapter<MovieRecyclerAdapter.recyclerViewHolder>() {

    /*private var filterList: ArrayList<MovieItem> = ArrayList(recyclerList)
    private var anotherList: ArrayList<MovieItem> = ArrayList()*/

    interface onMovieClickListener {
        fun onMovieClickListener(position: Int) {

        }
    }
    inner class recyclerViewHolder(view:View): RecyclerView.ViewHolder(view), View.OnClickListener {
        val movieImage = view.movieImage
        val movieName = view.movieName
        val movieScore = view.movieScore

        init {
            view.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            val position: Int = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onMovieClickListener(position)
            }
        }

    }

    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): recyclerViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.movie_item, p0, false)
        return recyclerViewHolder(view)
    }

    override fun onBindViewHolder(p0: recyclerViewHolder, p1: Int) {
        val movieItem = recyclerList?.get(p1)
        val url = StringBuilder("https://image.tmdb.org/t/p/w500")

        //p1? what should be passed in?
        p0.movieName.text = movieItem?.title

        val score = StringBuilder(movieItem?.vote_average.toString())
        score.append("/10")
        p0.movieScore.text = score.toString()


        url.append(movieItem?.poster_path)
        Picasso.get().load(url.toString()).resize(300,350).into(p0.movieImage)
    }

    override fun getItemCount(): Int {
        return recyclerList!!.size
    }

}