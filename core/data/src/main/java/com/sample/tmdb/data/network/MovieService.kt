package com.sample.tmdb.data.network

import com.sample.tmdb.data.response.MovieDetailResponse
import com.sample.tmdb.data.response.NetworkCreditWrapper
import com.sample.tmdb.data.response.NetworkMovie
import com.sample.tmdb.data.response.TMDbWrapper
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("3/trending/movie/day")
    suspend fun trendingMovies(): TMDbWrapper<NetworkMovie>

    @GET("3/movie/popular")
    suspend fun popularMovies(): TMDbWrapper<NetworkMovie>

    @GET("3/movie/now_playing")
    suspend fun nowPlayingMovies(): TMDbWrapper<NetworkMovie>

    @GET("3/movie/upcoming")
    suspend fun upcomingMovies(): TMDbWrapper<NetworkMovie>

    @GET("3/movie/top_rated")
    suspend fun topRatedMovies(): TMDbWrapper<NetworkMovie>

    @GET("3/discover/movie")
    suspend fun discoverMovies(): TMDbWrapper<NetworkMovie>

    @GET("3/trending/movie/day")
    suspend fun trendingMovies(@Query("page") page: Int): TMDbWrapper<NetworkMovie>

    @GET("3/trending/movie/week")
    suspend fun trendingWeekMovies(@Query("page") page: Int): TMDbWrapper<NetworkMovie>

    @GET("3/movie/popular")
    suspend fun popularMovies(@Query("page") page: Int): TMDbWrapper<NetworkMovie>

    @GET("3/movie/now_playing")
    suspend fun nowPlayingMovies(@Query("page") page: Int): TMDbWrapper<NetworkMovie>

    @GET("3/movie/upcoming")
    suspend fun upcomingMovies(@Query("page") page: Int): TMDbWrapper<NetworkMovie>

    @GET("3/movie/top_rated")
    suspend fun topRatedMovies(@Query("page") page: Int): TMDbWrapper<NetworkMovie>

    @GET("3/discover/movie")
    suspend fun discoverMovies(@Query("page") page: Int): TMDbWrapper<NetworkMovie>

    @GET("3/movie/{movieId}/credits")
    suspend fun movieCredit(@Path("movieId") movieId: Int): NetworkCreditWrapper

    @GET("3/movie/{movie_id}")
    suspend fun fetchMovieDetail(@Path("movie_id") movieId: Int): MovieDetailResponse

    @GET("3/movie/{movie_id}/similar")
    suspend fun fetchSimilarMovieDetail(@Path("movie_id") movieId: Int): TMDbWrapper<NetworkMovie>

    @GET("3/search/movie")
    suspend fun searchMovies(
        @Query("page") page: Int,
        @Query("query") query: String
    ): TMDbWrapper<NetworkMovie>
}