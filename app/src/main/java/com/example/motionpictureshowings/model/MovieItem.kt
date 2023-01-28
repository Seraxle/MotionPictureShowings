package com.example.motionpictureshowings.model

data class MovieItem(
    val page: Int,
    val results: MovieResults,
    val dates: MovieDates,
    val total_pages: Int,
    val total_results: Int
)