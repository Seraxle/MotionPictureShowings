package com.example.motionpictureshowings.model

data class TvResults(
    val poster_path: String?,
    val popularity: Number,
    val id: Int,
    val backdrop_path: String?,
    val vote_average: Number,
    val overview: String,
    val first_air_date: String,
    val origin_country: List<String>,
    val genre_ids: List<Int>,
    val original_language: String,
    val vote_count: Int,
    val name: String,
    val original_name: String
)