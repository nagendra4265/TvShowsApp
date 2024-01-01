package com.sample.tmdb.data.paging.tvshow

import android.content.Context
import com.sample.tmdb.data.network.TVShowService
import com.sample.tmdb.data.response.asTVShowDomainModel
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.paging.BasePagingSource

class SearchTvSeriesPagingSource(
    context: Context,
    private val tvShowApi: TVShowService,
    private val query: String
) : BasePagingSource<TVShow>(context) {

    override suspend fun fetchItems(page: Int): List<TVShow> {
        return if(query.isNotEmpty() && query.trim().isNotEmpty())
            tvShowApi.searchTVSeries(page, query).items.asTVShowDomainModel()
        else fetchWeekItems(page)
    }

    override suspend fun fetchWeekItems(page: Int): List<TVShow> =
        tvShowApi.trendingWeekTVSeries(page).items.asTVShowDomainModel()
}