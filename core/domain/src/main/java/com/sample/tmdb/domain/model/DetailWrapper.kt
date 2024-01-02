package com.sample.tmdb.domain.model


class DetailWrapper<T : TMDbItemDetails>(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val details: T,
    val moviesTvList: List<Movie>,
    val seasonsList: List<Seasons> = emptyList()
)