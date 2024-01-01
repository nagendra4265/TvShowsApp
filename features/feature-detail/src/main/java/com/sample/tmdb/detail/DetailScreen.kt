package com.sample.tmdb.detail

import androidx.annotation.StringRes
import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material.icons.rounded.OpenInNew
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.graphics.drawable.toBitmap
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.sample.tmdb.common.model.Credit
import com.sample.tmdb.common.model.TMDbItem
import com.sample.tmdb.common.ui.Content
import com.sample.tmdb.common.ui.Dimens
import com.sample.tmdb.common.ui.component.PersonCard
import com.sample.tmdb.common.utils.dpToPx
import com.sample.tmdb.common.utils.toDp
import com.sample.tmdb.detail.utils.openInChromeCustomTab
import com.sample.tmdb.domain.model.Cast
import com.sample.tmdb.domain.model.Crew
import com.sample.tmdb.domain.model.Genre
import com.sample.tmdb.domain.model.Movie
import com.sample.tmdb.domain.model.MovieCard
import com.sample.tmdb.domain.model.TMDbItemDetails
import com.sample.tmdb.domain.model.TVShow
import com.sample.tmdb.common.R as R1

@Composable
fun MovieDetailScreen(
    upPress: () -> Unit,
    onAllCastSelected: (List<Cast>) -> Unit,
    onAllCrewSelected: (List<Crew>) -> Unit,
    onCreditSelected: (String) -> Unit,
    onAllMoviesSelected: (List<Movie>) -> Unit,
    onMovieSelected: (String) -> Unit,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    DetailScreen(
        viewModel = viewModel,
        upPress = upPress,
        onAllCastSelected = onAllCastSelected,
        onAllCrewSelected = onAllCrewSelected,
        onCreditSelected = onCreditSelected,
        onAllMoviesSelected = onAllMoviesSelected,
        onMovieSelected = onMovieSelected
    ) { details ->
        Movie(
            id = details.id,
            overview = details.overview,
            releaseDate = details.releaseDate,
            backdropUrl = details.backdropPath,
            posterUrl = details.posterPath,
            name = details.title,
            voteAverage = details.voteAverage,
            voteCount = details.voteCount
        )
    }
}

@Composable
fun TVShowDetailScreen(
    upPress: () -> Unit,
    onAllCastSelected: (List<Cast>) -> Unit,
    onAllCrewSelected: (List<Crew>) -> Unit,
    onCreditSelected: (String) -> Unit,
    onAllMoviesSelected: (List<Movie>) -> Unit,
    onMovieSelected: (String) -> Unit,
    viewModel: TVShowDetailViewModel = hiltViewModel()
) {
    DetailScreen(
        viewModel = viewModel,
        upPress = upPress,
        onAllCastSelected = onAllCastSelected,
        onAllCrewSelected = onAllCrewSelected,
        onAllMoviesSelected = onAllMoviesSelected,
        onCreditSelected = onCreditSelected,
        onMovieSelected = onMovieSelected,
    ) { details ->
        TVShow(
            id = details.id,
            overview = details.overview,
            releaseDate = details.releaseDate,
            backdropUrl = details.backdropPath,
            posterUrl = details.posterPath,
            name = details.title,
            voteAverage = details.voteAverage,
            voteCount = details.voteCount
        )
    }
}

@Composable
private fun <T : TMDbItemDetails, E : TMDbItem> DetailScreen(
    viewModel: BaseDetailViewModel<T, E>,
    upPress: () -> Unit,
    onAllCastSelected: (List<Cast>) -> Unit,
    onAllCrewSelected: (List<Crew>) -> Unit,
    onCreditSelected: (String) -> Unit,
    onAllMoviesSelected: (List<Movie>) -> Unit,
    onMovieSelected: (String) -> Unit,
    getBookmarkedItem: (T) -> E
) {
    DetailScreen(
        viewModel = viewModel,
        upPress = upPress,
        onAllCastSelected = onAllCastSelected,
        onAllCrewSelected = onAllCrewSelected,
        onCreditSelected = onCreditSelected,
        onAllMoviesSelected = onAllMoviesSelected,
        onMovieSelected = onMovieSelected,
        fab = { isFabVisible, isBookmark, details ->
            ToggleBookmarkFab(isBookmark = isBookmark, isVisible = isFabVisible) {
                if (isBookmark) {
                    viewModel.removeBookmark(details.id)
                } else {
                    viewModel.addBookmark(getBookmarkedItem.invoke(details))
                }
            }
        }
    )
}

private val localVibrantColor =
    compositionLocalOf<Animatable<Color, AnimationVector4D>> { error("No vibrant color defined") }

@Composable
fun <T : TMDbItemDetails, E : TMDbItem> DetailScreen(
    viewModel: BaseDetailViewModel<T, E>,
    upPress: () -> Unit,
    onAllCastSelected: (List<Cast>) -> Unit,
    onAllCrewSelected: (List<Crew>) -> Unit,
    onCreditSelected: (String) -> Unit,
    onAllMoviesSelected: (List<Movie>) -> Unit,
    onMovieSelected: (String) -> Unit,
    fab: @Composable (MutableState<Boolean>, Boolean, T) -> Unit
) {
    // Visibility for FAB
    val isFabVisible = rememberSaveable { mutableStateOf(true) }
    val defaultTextColor = MaterialTheme.colors.onBackground
    val vibrantColor = remember { Animatable(defaultTextColor) }
    Content(viewModel = viewModel) {
        viewModel.isBookmarked(it.details.id)
        // Nested scroll for control FAB
        val nestedScrollConnection = remember {
            object : NestedScrollConnection {
                override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                    // Hide FAB
                    if (available.y < -1) {
                        isFabVisible.value = false
                    }
                    // Show FAB
                    if (available.y > 1) {
                        isFabVisible.value = true
                    }
                    return Offset.Zero
                }
            }
        }
        CompositionLocalProvider(
            localVibrantColor provides vibrantColor,
        ) {
            Scaffold(
                Modifier.nestedScroll(nestedScrollConnection),
                floatingActionButton = {
                    fab.invoke(
                        isFabVisible,
                        viewModel.isBookmarked.collectAsState().value,
                        it.details,
                    )
                },
                floatingActionButtonPosition = FabPosition.End
            ) { contentPadding ->
                ConstraintLayout(
                    Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.surface)
                        .verticalScroll(rememberScrollState())
                        .padding(contentPadding)
                ) {
                    val (appbar, backdrop, poster, title, originalTitle, genres, specs, rateStars, tagline, overview) = createRefs()
                    val (castSection, crewSection, similarMoviesSection, space) = createRefs()
                    val startGuideline = createGuidelineFromStart(16.dp)
                    val endGuideline = createGuidelineFromEnd(16.dp)

                    it.details.posterPath?.let { posterPath ->
                        GetVibrantColorFromPoster(
                            posterPath,
                            localVibrantColor.current
                        )
                    }
                    it.details.backdropPath?.let { backdropPath ->
                        Backdrop(
                            backdropUrl = backdropPath,
                            it.details.title,
                            Modifier.constrainAs(backdrop) {})
                    }
                    val posterWidth = 160.dp
                    AppBar(
                        homepage = it.details.homepage,
                        upPress = upPress,
                        modifier = Modifier
                            .requiredWidth(posterWidth * 2.2f)
                            .constrainAs(appbar) { centerTo(poster) }
                            .offset(y = 24.dp)
                    )
                    Poster(
                        it.details.posterPath,
                        it.details.title,
                        Modifier
                            .zIndex(17f)
                            .width(posterWidth)
                            .height(240.dp)
                            .constrainAs(poster) {
                                centerAround(backdrop.bottom)
                                linkTo(startGuideline, endGuideline)
                            }
                    )
                    Text(
                        text = it.details.title,
                        style = MaterialTheme.typography.h1.copy(
                            fontSize = 26.sp,
                            letterSpacing = 3.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier
                            .padding(horizontal = Dimens.PaddingLarge)
                            .constrainAs(title) {
                                top.linkTo(poster.bottom, 8.dp)
                                linkTo(startGuideline, endGuideline)
                            }
                    )
                    if (it.details.title != it.details.originalTitle) {
                        Text(
                            text = "(${it.details.originalTitle})",
                            style = MaterialTheme.typography.subtitle2.copy(
                                fontStyle = FontStyle.Italic,
                                letterSpacing = 2.sp
                            ),
                            modifier = Modifier
                                .padding(horizontal = Dimens.PaddingLarge)
                                .constrainAs(originalTitle) {
                                    top.linkTo(title.bottom)
                                    linkTo(startGuideline, endGuideline)
                                }
                        )
                    } else {
                        Spacer(
                            modifier = Modifier.constrainAs(originalTitle) {
                                top.linkTo(title.bottom)
                                linkTo(startGuideline, endGuideline)
                            }
                        )
                    }

                    GenreChips(
                        it.details.genres.take(4),
                        modifier = Modifier.constrainAs(genres) {
                            top.linkTo(originalTitle.bottom, 16.dp)
                            linkTo(startGuideline, endGuideline)
                        }
                    )

                    TMDbItemFields(
                        it.details,
                        modifier = Modifier.constrainAs(specs) {
                            top.linkTo(genres.bottom, 12.dp)
                            linkTo(startGuideline, endGuideline)
                        }
                    )

                    RateStars(
                        it.details.voteAverage,
                        modifier = Modifier.constrainAs(rateStars) {
                            top.linkTo(specs.bottom, 12.dp)
                            linkTo(startGuideline, endGuideline)
                        }
                    )

                    Text(
                        text = it.details.tagline,
                        color = localVibrantColor.current.value,
                        style = MaterialTheme.typography.body1.copy(
                            letterSpacing = 2.sp,
                            lineHeight = 24.sp,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .padding(horizontal = Dimens.PaddingLarge)
                            .constrainAs(tagline) {
                                top.linkTo(rateStars.bottom, 32.dp)
                            }
                    )

                    Text(
                        text = it.details.overview,
                        style = MaterialTheme.typography.body2.copy(
                            letterSpacing = 2.sp,
                            lineHeight = 30.sp,
                            fontFamily = FontFamily.SansSerif
                        ),
                        modifier = Modifier
                            .padding(horizontal = Dimens.PaddingLarge)
                            .constrainAs(overview) {
                                top.linkTo(tagline.bottom, 8.dp)
                                linkTo(startGuideline, endGuideline)
                            }
                    )
                    CreditSection(
                        items = it.cast,
                        headerResId = R.string.cast,
                        itemContent = { item, _ ->
                            PersonCard(item, onCreditSelected, Modifier.width(140.dp))
                        },
                        onAllCreditSelected = onAllCastSelected,
                        /*onSimilarMoviesSelected = onAllCastSelected,
                        onSimilarTvShowSelected = onAllCastSelected,*/
                        modifier = Modifier.constrainAs(castSection) {
                            top.linkTo(overview.bottom, 16.dp)
                            linkTo(startGuideline, endGuideline)
                        }
                    )
                    CreditSection(
                        items = it.crew,
                        headerResId = R.string.crew,
                        itemContent = { item, _ ->
                            PersonCard(item, onCreditSelected, Modifier.width(140.dp))
                        },
                        onAllCreditSelected = onAllCrewSelected,
                        /*onSimilarMoviesSelected = onAllCrewSelected,
                        onSimilarTvShowSelected = onAllCrewSelected,*/
                        modifier = Modifier.constrainAs(crewSection) {
                            top.linkTo(castSection.bottom, 16.dp)
                            linkTo(startGuideline, endGuideline)
                        }
                    )
                    MovieTvShowSection(
                        items = it.moviesTvList,
                        headerResId = R.string.similar,
                        itemContent = { item, _ ->
                            MovieCard(item, onMovieSelected, Modifier.width(140.dp))
                        },
                        onAllMoviesSelected = onAllMoviesSelected,
                        modifier = Modifier.constrainAs(similarMoviesSection) {
                            top.linkTo(
                                if (it.crew.isNotEmpty()) crewSection.bottom
                                else if (it.cast.isNotEmpty()) castSection.bottom
                                else overview.bottom,
                                16.dp
                            )
                            linkTo(startGuideline, endGuideline)
                        }
                    )

                    Spacer(
                        modifier = Modifier
                            .windowInsetsBottomHeight(WindowInsets.navigationBars)
                            .constrainAs(space) {
                                top.linkTo(
                                    if (it.moviesTvList.isNotEmpty()) similarMoviesSection.bottom
                                    else if (it.crew.isNotEmpty()) crewSection.bottom
                                    else if (it.cast.isNotEmpty()) castSection.bottom
                                    else overview.bottom
                                )
                            }
                    )
                }
            }
        }
    }
}

@Composable
fun GetVibrantColorFromPoster(
    posterUrl: String,
    color: Animatable<Color, AnimationVector4D>
) {
    val context = LocalContext.current
    LaunchedEffect(posterUrl) {
        val loader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(posterUrl)
            .size(128, 128)
            .allowHardware(false)
            .build()

        val bitmap = (loader.execute(request) as? SuccessResult)?.drawable?.toBitmap()
            ?: return@LaunchedEffect
        val vibrantColor = Palette.from(bitmap).generate().getVibrantColor(color.value.toArgb())
        color.animateTo(Color(vibrantColor), tween(400))
    }
}

@Composable
private fun Backdrop(backdropUrl: String, tmdbItemName: String, modifier: Modifier) {
    Card(
        elevation = 16.dp,
        shape = BottomArcShape(arcHeight = 120.dpToPx()),
        backgroundColor = localVibrantColor.current.value.copy(alpha = 0.1f),
        modifier = modifier.height(360.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(data = backdropUrl)
                .crossfade(1500).build(),
            contentScale = ContentScale.FillHeight,
            contentDescription = tmdbItemName,
            modifier = modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun AppBar(modifier: Modifier, homepage: String?, upPress: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        val vibrantColor = localVibrantColor.current.value
        val scaleModifier = Modifier.scale(1.1f)
        IconButton(onClick = { upPress.invoke() }) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = stringResource(id = R1.string.back),
                tint = vibrantColor,
                modifier = scaleModifier
            )
        }
        if (!homepage.isNullOrBlank()) {
            val context = LocalContext.current
            IconButton(onClick = { homepage.openInChromeCustomTab(context, vibrantColor) }) {
                Icon(
                    Icons.Rounded.OpenInNew,
                    contentDescription = stringResource(id = R.string.open),
                    tint = vibrantColor,
                    modifier = scaleModifier
                )
            }
        }
    }
}

private val springAnimation = spring(
    dampingRatio = Spring.DampingRatioMediumBouncy,
    stiffness = Spring.StiffnessLow,
    visibilityThreshold = 0.001f
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun Poster(posterUrl: String?, tmdbItemName: String, modifier: Modifier) {
    val isScaled = remember { mutableStateOf(false) }
    val scale =
        animateFloatAsState(
            targetValue = if (isScaled.value) 2.2f else 1f,
            animationSpec = springAnimation
        ).value

    Card(
        elevation = 24.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.scale(scale),
        onClick = { isScaled.value = !isScaled.value }
    ) {
        AsyncImage(
            model = posterUrl,
            contentDescription = tmdbItemName,
            contentScale = ContentScale.FillHeight
        )
    }
}

@Composable
private fun GenreChips(genres: List<Genre>, modifier: Modifier) {
    Row(
        modifier
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = Dimens.PaddingLarge)
    ) {
        genres.map(Genre::name).forEachIndexed { index, name ->
            Text(
                text = name.orEmpty(),
                style = MaterialTheme.typography.subtitle1.copy(letterSpacing = 2.sp),
                modifier = Modifier
                    .border(1.25.dp, localVibrantColor.current.value, RoundedCornerShape(50))
                    .padding(horizontal = Dimens.PaddingSmall, vertical = 3.dp)
            )

            if (index != genres.lastIndex) {
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Composable
private fun TMDbItemFields(tmdbItemDetails: TMDbItemDetails, modifier: Modifier) {
    Row(horizontalArrangement = Arrangement.spacedBy(20.dp), modifier = modifier) {
        val context = LocalContext.current
        tmdbItemDetails.releaseDate?.let {
            TMDbItemField(
                context.getString(R.string.release_date),
                it
            )
        }
        TMDbItemField(
            context.getString(R.string.vote_average),
            tmdbItemDetails.voteAverage.toString()
        )
        TMDbItemField(context.getString(R.string.votes), tmdbItemDetails.voteCount.toString())
    }
}

@Composable
private fun TMDbItemField(name: String, value: String) {
    Column {
        Text(
            text = name,
            style = MaterialTheme.typography.subtitle2.copy(
                fontSize = 13.sp,
                letterSpacing = 1.sp
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.SemiBold),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = Dimens.PaddingExtraSmall)
        )
    }
}

@Composable
private fun RateStars(voteAverage: Double, modifier: Modifier) {
    Row(modifier.padding(start = Dimens.PaddingExtraSmall)) {
        val maxVote = 10
        val starCount = 5
        repeat(starCount) { starIndex ->
            val voteStarCount = voteAverage / (maxVote / starCount)
            val asset = when {
                voteStarCount >= starIndex + 1 -> Icons.Filled.Star
                voteStarCount in starIndex.toDouble()..(starIndex + 1).toDouble() -> Icons.Filled.StarHalf
                else -> Icons.Filled.StarOutline
            }
            Icon(
                imageVector = asset,
                contentDescription = null,
                tint = localVibrantColor.current.value
            )
            Spacer(modifier = Modifier.width(4.dp))
        }
    }
}

@Composable
private fun <T : Credit> CreditSection(
    items: List<T>,
    @StringRes headerResId: Int,
    itemContent: @Composable (T, Int) -> Unit,
    onAllCreditSelected: (List<T>) -> Unit,
    modifier: Modifier
) {
    if (items.isNotEmpty()) {
        Column(modifier = modifier.fillMaxWidth()) {
            SectionHeader(headerResId, items, onAllCreditSelected)
            LazyRow(
                modifier = Modifier.testTag(LocalContext.current.getString(headerResId)),
                contentPadding = PaddingValues(Dimens.PaddingLarge)
            ) {
                items(
                    count = items.size,
                    itemContent = { index ->
                        itemContent(items[index], index)
                        Spacer(modifier = Modifier.width(Dimens.PaddingLarge))
                    }
                )
            }
        }
    }
}

@Composable
private fun MovieTvShowSection(
    items: List<Movie>,
    @StringRes headerResId: Int,
    itemContent: @Composable (Movie, Int) -> Unit,
    onAllMoviesSelected: (List<Movie>) -> Unit,
    modifier: Modifier
) {
    if (items.isNotEmpty()) {
        Column(modifier = modifier.fillMaxWidth()) {
            SectionHeader1(headerResId, items, onAllMoviesSelected)
            LazyRow(
                modifier = Modifier.testTag(LocalContext.current.getString(headerResId)),
                contentPadding = PaddingValues(Dimens.PaddingLarge)
            ) {
                items(
                    count = items.size,
                    itemContent = { index ->
                        itemContent(items[index], index)
                        Spacer(modifier = Modifier.width(Dimens.PaddingLarge))
                    }
                )
            }
        }
    }
}

@Composable
private fun <T : Credit> SectionHeader(
    @StringRes headerResId: Int,
    items: List<T>,
    onAllCreditSelected: (List<T>) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.PaddingLarge)
    ) {
        Text(
            text = stringResource(headerResId),
            color = localVibrantColor.current.value,
            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(Dimens.PaddingExtraSmall)
                .clickable {
                    onAllCreditSelected.invoke(items)
                }
        ) {
            Text(
                text = stringResource(R.string.see_all, items.size),
                color = localVibrantColor.current.value,
                style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(end = Dimens.PaddingExtraSmall)
            )
            Icon(
                Icons.Filled.ArrowForward,
                contentDescription = stringResource(R.string.see_all),
                tint = localVibrantColor.current.value,
            )
        }
    }
}


@Composable
private fun SectionHeader1(
    @StringRes headerResId: Int,
    items: List<Movie>,
    onAllMoviesSelected: (List<Movie>) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.PaddingLarge)
    ) {
        Text(
            text = stringResource(headerResId),
            color = localVibrantColor.current.value,
            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(Dimens.PaddingExtraSmall)
                /*.clickable {
                    onAllMoviesSelected.invoke(items)
                }*/
        ) {
            Text(
                text = stringResource(R.string.see_all, items.size),
                color = localVibrantColor.current.value,
                style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(end = Dimens.PaddingExtraSmall)
            )
            Icon(
                Icons.Filled.ArrowForward,
                contentDescription = stringResource(R.string.see_all),
                tint = localVibrantColor.current.value,
            )
        }
    }
}

@Composable
private fun ToggleBookmarkFab(
    isBookmark: Boolean,
    isVisible: MutableState<Boolean>,
    onClick: () -> Unit
) {
    AnimatedVisibility(
        visible = isVisible.value,
        enter = slideInVertically(initialOffsetY = { it * 2 }),
        exit = slideOutVertically(targetOffsetY = { it * 2 }),
    ) {
        FloatingActionButton(
            modifier = Modifier.padding(
                bottom = WindowInsets.navigationBars
                    .getBottom(LocalDensity.current).toDp().dp
            ),
            shape = CircleShape,
            onClick = onClick
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                tint = if (isBookmark) Color.Red else MaterialTheme.colors.surface,
                contentDescription = if (isBookmark) stringResource(R.string.favorite) else stringResource(
                    R.string.un_favorite
                )
            )
        }
    }
}

class BottomArcShape(private val arcHeight: Float) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            moveTo(size.width, 0f)
            lineTo(size.width, size.height)
            val arcOffset = arcHeight / 10
            val rect = Rect(
                left = 0f - arcOffset,
                top = size.height - arcHeight,
                right = size.width + arcOffset,
                bottom = size.height
            )
            arcTo(rect, 0f, 180f, false)
            lineTo(0f, 0f)
            close()
        }
        return Outline.Generic(path)
    }
}
