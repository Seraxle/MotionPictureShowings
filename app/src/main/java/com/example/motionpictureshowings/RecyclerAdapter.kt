package com.example.motionpictureshowings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.motionpictureshowings.model.ShowingItem

class RecyclerAdapter(
    private val recyclerList: ArrayList<ShowingItem>,
    private val listener: onShowingClickListener
) : RecyclerView.Adapter<RecyclerAdapter.recyclerViewHolder>(), Filterable {

    private var filterList: ArrayList<ShowingItem> = ArrayList(recyclerList)
    private var anotherList: ArrayList<ShowingItem> = ArrayList()

    interface onShowingClickListener {
        fun onShowingClickListener(position: Int) {

        }
    }
    inner class recyclerViewHolder(view:View): RecyclerView.ViewHolder(view), View.OnClickListener {
//values here

        init {
            view.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            val position: Int = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onShowingClickListener(position)
            }
        }

    }

    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): recyclerViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.showing_item, p0, false)
        return recyclerViewHolder(view)
    }

    override fun onBindViewHolder(p0: RecyclerAdapter.recyclerViewHolder, p1: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return recyclerList.size
    }

    override fun getFilter(): Filter {
        return showingFilter
    }

    private var showingFilter = object: Filter() {
        override fun performFiltering(p0: CharSequence?): FilterResults? {
            var filteredList: ArrayList<ShowingItem> = ArrayList()
            if(p0 == null || p0.length == 0) filteredList.addAll(filterList)
            else {
                var filterPattern: String = p0.toString().lowercase().trim()
                for (i in 0 until recyclerList.size) {
                    if (recyclerList[i].showingName.lowercase().contains(filterPattern)) {
                        filterList.add(recyclerList[i])
                    }
                }
            }

            var results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
            recyclerList.clear()
            if (p1 != null) recyclerList.addAll(p1.values as ArrayList<ShowingItem>)
            notifyDataSetChanged()
        }
    }


}