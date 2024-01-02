package com.sample.tmdb.domain.model

data class Seasons(
    val airDate: String? = null,
    val episodeCount: Int? = 0,
    val id: Int? = 0,
    val name: String = "",
    val overview: String? = "",
    val posterPath: String? = "",
    val seasonNumber: Int? = 0,
    val voteAverage: Double? = 0.0
)
