package com.sample.tmdb.data.repository

import android.content.Context
import com.sample.tmdb.common.base.BaseRepository
import com.sample.tmdb.data.di.IoDispatcher
import com.sample.tmdb.data.source.entity.asDomainModel
import com.sample.tmdb.data.source.local.MovieDao
import com.sample.tmdb.domain.model.Movie
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookmarkMovieRepository @Inject constructor(
    private val movieDao: MovieDao,
    @ApplicationContext context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
) : BaseRepository<List<@JvmSuppressWildcards Movie>>(context, ioDispatcher) {

    override suspend fun getSuccessResult(id: Any?): List<Movie> =
        movieDao.getBookmarks().asDomainModel()
}