package com.example.motionpictureshowings.model

data class TvItem(
    val page: Int,
    val results: List<TvResults>,
    val total_results: Int,
    val total_pages: Int
)