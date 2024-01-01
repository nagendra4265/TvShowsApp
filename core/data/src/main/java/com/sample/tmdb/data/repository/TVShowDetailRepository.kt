package com.sample.tmdb.data.repository

import android.content.Context
import com.sample.tmdb.common.utils.Constants
import com.sample.tmdb.data.network.TVShowService
import com.sample.tmdb.data.response.asCastDomainModel
import com.sample.tmdb.data.response.asCrewDomainModel
import com.sample.tmdb.data.response.asDomainModel
import com.sample.tmdb.data.di.IoDispatcher
import com.sample.tmdb.data.response.asMovieDomainModel
import com.sample.tmdb.data.response.asTVShowDomainModel
import com.sample.tmdb.domain.model.Cast
import com.sample.tmdb.domain.model.Crew
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.domain.repository.BaseDetailRepository
import com.sample.tmdb.domain.model.TvDetails
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TVShowDetailRepository @Inject constructor(
    private val tvShowApi: TVShowService,
    @ApplicationContext context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : BaseDetailRepository<TvDetails>(context, ioDispatcher) {

    override suspend fun getDetails(id: Int): TvDetails =
        tvShowApi.fetchTvDetail(id).asDomainModel()

    override suspend fun getCredit(id: Int): Pair<List<Cast>, List<Crew>> {
        val networkCreditWrapper = tvShowApi.tvCredit(id)
        return Pair(
            networkCreditWrapper.cast.asCastDomainModel(),
            networkCreditWrapper.crew.asCrewDomainModel()
        )
    }

    override suspend fun getMoviesTvShows(id: Int): List<Movie> {
        val tvShows = tvShowApi.fetchSimilarTvDetail(id).items.asTVShowDomainModel()
        return tvShows.map {
            Movie(
                id = it.id,
                overview = it.overview,
                releaseDate = it.releaseDate,
                posterUrl = it.posterUrl?.let { posterPath ->
                    String.format(
                        Constants.BASE_WIDTH_342_PATH,
                        posterPath
                    )
                },
                backdropUrl = it.backdropUrl?.let { backdropPath ->
                    String.format(
                        Constants.BASE_WIDTH_780_PATH,
                        backdropPath
                    )
                },
                name = it.name,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount
            )

        }

    }

}