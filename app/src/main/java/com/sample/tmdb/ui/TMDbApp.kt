package com.sample.tmdb.ui

import android.net.Uri
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Tv
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sample.tmdb.R
import com.sample.tmdb.bookmark.BookmarkScreen
import com.sample.tmdb.common.MainDestinations
import com.sample.tmdb.credit.CreditScreen
import com.sample.tmdb.credit.MovieScreen
import com.sample.tmdb.credit.PersonScreen
import com.sample.tmdb.detail.MovieDetailScreen
import com.sample.tmdb.detail.TVShowDetailScreen
import com.sample.tmdb.domain.model.Cast
import com.sample.tmdb.domain.model.Crew
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.feed.MovieFeedScreen
import com.sample.tmdb.feed.TVShowFeedScreen
import com.sample.tmdb.paging.AiringTodayTVShowScreen
import com.sample.tmdb.paging.DiscoverMovieScreen
import com.sample.tmdb.paging.DiscoverTVShowScreen
import com.sample.tmdb.paging.NowPlayingMovieScreen
import com.sample.tmdb.paging.OnTheAirTVShowScreen
import com.sample.tmdb.paging.PopularMovieScreen
import com.sample.tmdb.paging.PopularTVShowScreen
import com.sample.tmdb.paging.TopRatedMovieScreen
import com.sample.tmdb.paging.TopRatedTVShowScreen
import com.sample.tmdb.paging.TrendingMovieScreen
import com.sample.tmdb.paging.TrendingTVShowScreen
import com.sample.tmdb.paging.UpcomingMovieScreen
import com.sample.tmdb.paging.search.SearchMoviesScreen
import com.sample.tmdb.paging.search.SearchTVSeriesScreen

@Composable
fun TMDbApp() {
    val appState = rememberTMDbkAppState()
    Scaffold(
        bottomBar = {
            if (appState.shouldShowBottomBar) {
                TMDbBottomBar(
                    tabs = appState.bottomBarTabs,
                    currentRoute = appState.currentRoute!!,
                    navigateToRoute = appState::navigateToBottomBarRoute,
                )
            }
        }
    ) { innerPaddingModifier ->
        NavHost(
            navController = appState.navController,
            startDestination = MainDestinations.HOME_ROUTE,
            modifier = Modifier.padding(innerPaddingModifier)
        ) {
            navigationScreens(
                onMovieSelected = appState::navigateToMovieDetail,
                onTVShowSelected = appState::navigateToTVShowDetail,
                onSearchMovie = appState::navigateToSearchMovie,
                onSearchTVShow = appState::navigateToSearchTVShow,
                navController = appState.navController
            )
            detailScreens(
                onAllCastSelected = appState::navigateToCastList,
                onAllCrewSelected = appState::navigateToCrewList,
                onCreditSelected = appState::navigateToPerson,
                onAllMoviesSelected = appState::navigateToCrewList,
                onMovieSelected = appState::navigateToPerson,
                upPress = appState::upPress
            )
            moviePagingScreens(
                onMovieSelected = appState::navigateToMovieDetail,
                onSearchMovie = appState::navigateToSearchMovie,
                upPress = appState::upPress
            )
            tvShowPagingScreens(
                onTVShowSelected = appState::navigateToTVShowDetail,
                onSearchTVShow = appState::navigateToSearchTVShow,
                upPress = appState::upPress
            )
            searchScreens(
                onMovieSelected = appState::navigateToMovieDetail,
                onTVShowSelected = appState::navigateToTVShowDetail,
                upPress = appState::upPress
            )
            creditScreens(
                onCreditSelected = appState::navigateToPerson,
                upPress = appState::upPress
            )
            personScreen(upPress = appState::upPress)
        }
    }
}

@Composable
private fun TMDbBottomBar(
    tabs: Array<HomeSections>,
    currentRoute: String,
    navigateToRoute: (String) -> Unit
) {
    val currentSection = tabs.first { it.route == currentRoute }

    Box(
        Modifier.navigationBarsPadding()
    ) {
        BottomNavigation(backgroundColor = MaterialTheme.colors.background, elevation = 0.dp) {
            tabs.forEach { section ->
                val selected = section == currentSection
                BottomNavigationItem(
                    label = {
                        Text(text = stringResource(id = section.title))
                    },
                    icon = {
                        Icon(
                            imageVector = if (selected) section.selectedIcon else section.unselectedIcon,
                            contentDescription = stringResource(id = section.title)
                        )
                    },
                    selected = selected,
                    unselectedContentColor = MaterialTheme.colors.onBackground.copy(alpha = ContentAlpha.disabled),
                    selectedContentColor = MaterialTheme.colors.onBackground,
                    onClick = { navigateToRoute(section.route) }
                )
            }
        }
    }
}

private fun NavGraphBuilder.navigationScreens(
    onMovieSelected: (Int) -> Unit,
    onTVShowSelected: (Int) -> Unit,
    onSearchMovie: () -> Unit,
    onSearchTVShow: () -> Unit,
    navController: NavController
) {
    navigation(
        route = MainDestinations.HOME_ROUTE,
        startDestination = HomeSections.MOVIE_SECTION.route
    ) {
        composable(route = HomeSections.MOVIE_SECTION.route) {
            MovieFeedScreen(
                onClick = { onMovieSelected(it.id) },
                onSearchMovie = onSearchMovie,
                navController = navController
            )
        }
        composable(route = HomeSections.TV_SHOW_SECTION.route) {
            TVShowFeedScreen(
                onClick = { onTVShowSelected(it.id) },
                onSearchTVShow = onSearchTVShow,
                navController = navController,
            )
        }
        composable(route = HomeSections.BOOKMARK_SECTION.route) {
            BookmarkScreen(
                onClickMovie = { onMovieSelected(it.id) },
                onClickTVShow = { onTVShowSelected(it.id) })
        }
    }
}

private fun NavGraphBuilder.detailScreens(
    onAllCastSelected: (String) -> Unit,
    onAllCrewSelected: (String) -> Unit,
    onCreditSelected: (String) -> Unit,
    onAllMoviesSelected: (String) -> Unit,
    onMovieSelected: (String) -> Unit,
    upPress: () -> Unit
) {
    composable(
        route = "${MainDestinations.TMDB_MOVIE_DETAIL_ROUTE}/{${MainDestinations.TMDB_ID_KEY}}",
        arguments = listOf(
            navArgument(MainDestinations.TMDB_ID_KEY) { type = NavType.IntType })
    ) {
        MovieDetailScreen(upPress, onAllCastSelected = {
            onAllCastSelected(
                Uri.encode(gson.toJson(it, object : TypeToken<List<Cast>>() {}.type))
            )
        }, onAllCrewSelected = {
            onAllCrewSelected(
                Uri.encode(gson.toJson(it, object : TypeToken<List<Crew>>() {}.type))
            )
        }, onCreditSelected = {
            onCreditSelected(it)
        }, onAllMoviesSelected = {
            onAllMoviesSelected(
                Uri.encode(gson.toJson(it, object : TypeToken<List<Movie>>() {}.type))
            )
        }, onMovieSelected = {
            /*onMovieSelected(it)*/
        })
    }
    composable(
        route = "${MainDestinations.TMDB_TV_SHOW_DETAIL_ROUTE}/{${MainDestinations.TMDB_ID_KEY}}",
        arguments = listOf(
            navArgument(MainDestinations.TMDB_ID_KEY) { type = NavType.IntType })
    ) {
        TVShowDetailScreen(upPress, onAllCastSelected = {
            onAllCastSelected(
                Uri.encode(gson.toJson(it, object : TypeToken<List<Cast>>() {}.type))
            )
        }, onAllCrewSelected = {
            onAllCrewSelected(
                Uri.encode(gson.toJson(it, object : TypeToken<List<Crew>>() {}.type))
            )
        }, onCreditSelected = {
            onCreditSelected(it)
        }, onAllMoviesSelected = {
            onAllMoviesSelected(
                Uri.encode(gson.toJson(it, object : TypeToken<List<Movie>>() {}.type))
            )
        }, onMovieSelected = {
            onMovieSelected(it)
        })
    }
}

private fun NavGraphBuilder.moviePagingScreens(
    onMovieSelected: (Int) -> Unit,
    onSearchMovie: () -> Unit,
    upPress: () -> Unit
) {
    composable(route = MainDestinations.TMDB_TRENDING_MOVIES_ROUTE) {
        TrendingMovieScreen(
            onClick = { onMovieSelected(it.id) },
            upPress = upPress,
            onSearchMovie = onSearchMovie
        )
    }
    composable(route = MainDestinations.TMDB_POPULAR_MOVIES_ROUTE) {
        PopularMovieScreen(
            onClick = { onMovieSelected(it.id) },
            upPress = upPress,
            onSearchMovie = onSearchMovie
        )
    }
    composable(route = MainDestinations.TMDB_NOW_PLAYING_MOVIES_ROUTE) {
        NowPlayingMovieScreen(
            onClick = { onMovieSelected(it.id) },
            upPress = upPress,
            onSearchMovie = onSearchMovie
        )
    }
    composable(route = MainDestinations.TMDB_UPCOMING_MOVIES_ROUTE) {
        UpcomingMovieScreen(
            onClick = { onMovieSelected(it.id) },
            upPress = upPress,
            onSearchMovie = onSearchMovie
        )
    }
    composable(route = MainDestinations.TMDB_TOP_RATED_MOVIES_ROUTE) {
        TopRatedMovieScreen(
            onClick = { onMovieSelected(it.id) },
            upPress = upPress,
            onSearchMovie = onSearchMovie
        )
    }
    composable(route = MainDestinations.TMDB_DISCOVER_MOVIES_ROUTE) {
        DiscoverMovieScreen(
            onClick = { onMovieSelected(it.id) },
            upPress = upPress,
            onSearchMovie = onSearchMovie
        )
    }
}

private fun NavGraphBuilder.tvShowPagingScreens(
    onTVShowSelected: (Int) -> Unit,
    onSearchTVShow: () -> Unit,
    upPress: () -> Unit
) {
    composable(route = MainDestinations.TMDB_TRENDING_TV_SHOW_ROUTE) {
        TrendingTVShowScreen(
            onClick = { onTVShowSelected(it.id) },
            upPress = upPress,
            onSearchTVShow = onSearchTVShow
        )
    }
    composable(route = MainDestinations.TMDB_POPULAR_TV_SHOW_ROUTE) {
        PopularTVShowScreen(
            onClick = { onTVShowSelected(it.id) },
            upPress = upPress,
            onSearchTVShow = onSearchTVShow
        )
    }
    composable(route = MainDestinations.TMDB_AIRING_TODAY_TV_SHOW_ROUTE) {
        AiringTodayTVShowScreen(
            onClick = { onTVShowSelected(it.id) },
            upPress = upPress,
            onSearchTVShow = onSearchTVShow
        )
    }
    composable(route = MainDestinations.TMDB_ON_THE_AIR_TV_SHOW_ROUTE) {
        OnTheAirTVShowScreen(
            onClick = { onTVShowSelected(it.id) },
            upPress = upPress,
            onSearchTVShow = onSearchTVShow
        )
    }
    composable(route = MainDestinations.TMDB_TOP_RATED_TV_SHOW_ROUTE) {
        TopRatedTVShowScreen(
            onClick = { onTVShowSelected(it.id) },
            upPress = upPress,
            onSearchTVShow = onSearchTVShow
        )
    }
    composable(route = MainDestinations.TMDB_DISCOVER_TV_SHOW_ROUTE) {
        DiscoverTVShowScreen(
            onClick = { onTVShowSelected(it.id) },
            upPress = upPress,
            onSearchTVShow = onSearchTVShow
        )
    }
}

private fun NavGraphBuilder.searchScreens(
    onMovieSelected: (Int) -> Unit,
    onTVShowSelected: (Int) -> Unit,
    upPress: () -> Unit
) {
    composable(route = MainDestinations.TMDB_SEARCH_MOVIE_ROUTE) {
        SearchMoviesScreen(onClick = { onMovieSelected(it.id) }, upPress = upPress)
    }
    composable(route = MainDestinations.TMDB_SEARCH_TV_SHOW_ROUTE) {
        SearchTVSeriesScreen(onClick = { onTVShowSelected(it.id) }, upPress = upPress)
    }
    composable(route = MainDestinations.TMDB_SIMILAR_MOVIES_ROUTE) {
        SearchMoviesScreen(onClick = { onMovieSelected(it.id) }, upPress = upPress)
    }
    composable(route = MainDestinations.TMDB_SIMILAR_TV_SHOW_ROUTE) {
        SearchTVSeriesScreen(onClick = { onTVShowSelected(it.id) }, upPress = upPress)
    }
}

private fun NavGraphBuilder.creditScreens(
    onCreditSelected: (String) -> Unit,
    upPress: () -> Unit
) {
    composable(
        route = "${MainDestinations.TMDB_CAST_ROUTE}/{${MainDestinations.TMDB_CREDIT_KEY}}",
        arguments = listOf(
            navArgument(MainDestinations.TMDB_CREDIT_KEY) { type = NavType.StringType })
    ) { from ->
        CreditScreen(
            R.string.cast,
            onCreditSelected = {
                onCreditSelected(it)
            },
            upPress,
            gson.fromJson<List<Cast>>(
                from.arguments?.getString(MainDestinations.TMDB_CREDIT_KEY),
                object : TypeToken<List<Cast>>() {}.type
            )
        )
    }
    composable(
        route = "${MainDestinations.TMDB_CREW_ROUTE}/{${MainDestinations.TMDB_CREDIT_KEY}}",
        arguments = listOf(
            navArgument(MainDestinations.TMDB_CREDIT_KEY) { type = NavType.StringType })
    ) { from ->
        CreditScreen(
            R.string.crew,
            onCreditSelected = {
                onCreditSelected(it)
            },
            upPress,
            gson.fromJson<List<Crew>>(
                from.arguments?.getString(MainDestinations.TMDB_CREDIT_KEY),
                object : TypeToken<List<Crew>>() {}.type
            )
        )
    }
    composable(
        route = "${MainDestinations.TMDB_SIMILAR_MOVIES_ROUTE}/{${MainDestinations.TMDB_CREDIT_KEY}}",
        arguments = listOf(
            navArgument(MainDestinations.TMDB_CREDIT_KEY) { type = NavType.StringType })
    ) { from ->
        MovieScreen(
            R.string.similar,
            onMovieSelected = {
                onCreditSelected(it)
            },
            upPress,
            gson.fromJson(
                from.arguments?.getString(MainDestinations.TMDB_CREDIT_KEY),
                object : TypeToken<List<Movie>>() {}.type
            )
        )
    }
    /*
    composable(
        route = "${MainDestinations.TMDB_SIMILAR_TV_SHOW_ROUTE}/{${MainDestinations.TMDB_CREDIT_KEY}}",
        arguments = listOf(
            navArgument(MainDestinations.TMDB_CREDIT_KEY) { type = NavType.StringType })
    ) { from ->
        CreditScreen(
            R.string.similar_movies,
            onCreditSelected = {
                onCreditSelected(it)
            },
            upPress,
            gson.fromJson<List<TVShow>>(
                from.arguments?.getString(MainDestinations.TMDB_CREDIT_KEY),
                object : TypeToken<List<TVShow>>() {}.type
            )
        )
    }*/

}

private fun NavGraphBuilder.personScreen(
    upPress: () -> Unit
) {
    composable(
        route = "${MainDestinations.TMDB_PERSON_ROUTE}/{${MainDestinations.TMDB_PERSON_KEY}}",
        arguments = listOf(
            navArgument(MainDestinations.TMDB_PERSON_KEY) { type = NavType.StringType })
    ) {
        PersonScreen(upPress)
    }
}

enum class HomeSections(
    val route: String,
    @StringRes val title: Int,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
) {
    MOVIE_SECTION("Movie", R.string.movie, Icons.Outlined.Movie, Icons.Filled.Movie),
    TV_SHOW_SECTION("TVShow", R.string.tv_show, Icons.Outlined.Tv, Icons.Filled.Tv),
    BOOKMARK_SECTION("Bookmark", R.string.favorite, Icons.Outlined.Favorite, Icons.Filled.Favorite)
}

private val gson = Gson()