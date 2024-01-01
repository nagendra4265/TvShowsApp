package com.sample.tmdb.feed

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.model.TMDbType
import com.sample.tmdb.common.ui.Content
import com.sample.tmdb.common.ui.component.DestinationBar
import com.sample.tmdb.common.R as R1

@Composable
fun MovieFeedScreen(
    onClick: (TMDbItem) -> Unit,
    onSearchMovie: () -> Unit,
    navController: NavController,
    viewModel: MovieFeedViewModel = hiltViewModel()
) {
    FeedScreen(
        viewModel = viewModel,
        type = TMDbType.MOVIES,
        navController = navController,
        onSearchClicked = onSearchMovie,
        onClick = onClick,
        R1.string.movies
    )
}

@Composable
fun TVShowFeedScreen(
    onClick: (TMDbItem) -> Unit,
    onSearchTVShow: () -> Unit,
    navController: NavController,
    viewModel: TVShowFeedViewModel = hiltViewModel()
) {
    FeedScreen(
        viewModel = viewModel,
        type = TMDbType.TV_SERIES,
        navController = navController,
        onSearchClicked = onSearchTVShow,
        onClick = onClick,
        R1.string.tv_series
    )
}

@Composable
private fun <T : TMDbItem> FeedScreen(
    viewModel: BaseFeedViewModel<T>,
    type: TMDbType,
    navController: NavController,
    onSearchClicked: () -> Unit,
    onClick: (TMDbItem) -> Unit,
    @StringRes resourceId: Int

) {
    Content(viewModel = viewModel) { feeds ->
        Box {
            FeedCollectionList(type, navController, feeds, onClick)
            DestinationBar(
                title = stringResource(resourceId), onSearchClicked = onSearchClicked
            )
        }
    }
}