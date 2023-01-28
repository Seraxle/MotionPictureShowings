package com.example.motionpictureshowings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.motionpictureshowings.model.TvItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.tv_item.view.*

class TvRecyclerAdapter(
    private val recyclerList: ArrayList<TvItem>,
    private val listener: onTvClickListener
) : RecyclerView.Adapter<TvRecyclerAdapter.recyclerViewHolder>() {

    private var filterList: ArrayList<TvItem> = ArrayList(recyclerList)
    private var anotherList: ArrayList<TvItem> = ArrayList()

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

    override fun onBindViewHolder(p0: TvRecyclerAdapter.recyclerViewHolder, p1: Int) {
        val tvItem = recyclerList[p1]
        val url = StringBuilder("http://image.tmdb.org/t/p/w500")

        p0.tvName.text = tvItem.results.name
        p0.tvScore.text = tvItem.results.vote_average.toString()
        url.append(tvItem.results.poster_path)
        Picasso.get().load(url.toString()).into(p0.tvImage)
    }

    override fun getItemCount(): Int {
        return recyclerList.size
    }

}