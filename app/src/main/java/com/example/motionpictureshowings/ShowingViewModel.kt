package com.example.motionpictureshowings

import androidx.lifecycle.ViewModel
import com.example.motionpictureshowings.model.ShowingItem

class ShowingViewModel: ViewModel() {

    private var showingItemList: ArrayList<ShowingItem> = ArrayList()

    fun getShowingItemList() : ArrayList<ShowingItem> {
        return this.showingItemList
    }

    fun setShowingItemList(showingItemList: ArrayList<ShowingItem>) {
        this.showingItemList = showingItemList
    }
}