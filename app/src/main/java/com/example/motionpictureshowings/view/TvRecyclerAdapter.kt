package com.example.motionpictureshowings.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.motionpictureshowings.R
import com.example.motionpictureshowings.model.TvResults
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.tv_item.view.*

class TvRecyclerAdapter(
    private val recyclerList: List<TvResults>?,
    private val listener: onTvClickListener
) : RecyclerView.Adapter<TvRecyclerAdapter.recyclerViewHolder>() {

    /*private var filterList: ArrayList<TvItem> = ArrayList(recyclerList)
    private var anotherList: ArrayList<TvItem> = ArrayList()*/

    interface onTvClickListener {
        fun onTvClickListener(position: Int) {

        }
    }
    inner class recyclerViewHolder(view:View): RecyclerView.ViewHolder(view), View.OnClickListener {
        val tvImage = view.tvImage
        val tvName = view.tvName
        val tvScore = view.tvScore

        init {
            view.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            val position: Int = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onTvClickListener(position)
            }
        }

    }

    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): recyclerViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.tv_item, p0, false)
        return recyclerViewHolder(view)
    }

    override fun onBindViewHolder(p0: recyclerViewHolder, p1: Int) {
        val tvItem = recyclerList?.get(p1)
        val url = StringBuilder("https://image.tmdb.org/t/p/w500")

        p0.tvName.text = tvItem?.name

        val score = StringBuilder(tvItem?.vote_average.toString())
        score.append("/10")
        p0.tvScore.text = score.toString()

        url.append(tvItem?.poster_path)
        Picasso.get().load(url.toString()).into(p0.tvImage)
    }

    override fun getItemCount(): Int {
        return recyclerList!!.size
    }

}