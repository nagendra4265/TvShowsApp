package com.sample.tmdb.credit

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sample.tmdb.common.model.Credit
import com.sample.tmdb.common.ui.Dimens
import com.sample.tmdb.common.ui.component.DestinationBar
import com.sample.tmdb.common.ui.component.PersonCard
import com.sample.tmdb.common.utils.toDp
import com.sample.tmdb.domain.model.MovieCard

@Composable
fun <T : Credit> CreditScreen(
    @StringRes resourceId: Int,
    onCreditSelected: (String) -> Unit,
    upPress: () -> Unit,
    items: List<T>
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(Dimens.CreditCardSize),
        contentPadding = PaddingValues(
            start = Dimens.PaddingSmall,
            end = Dimens.PaddingSmall,
            top = Dimens.PaddingSmall,
            bottom = WindowInsets.navigationBars.getBottom(LocalDensity.current)
                .toDp().dp.plus(
                    Dimens.PaddingMedium
                )
        ),
        content = {

            item(span = {
                GridItemSpan(maxLineSpan)
            }) {
                Spacer(
                    Modifier.windowInsetsTopHeight(
                        WindowInsets.statusBars.add(WindowInsets(top = 56.dp))
                    )
                )
            }

            items(items.size) { index ->
                PersonCard(person = items[index], onCreditSelected = onCreditSelected)
            }
        }
    )
    DestinationBar(title = stringResource(resourceId), upPress = upPress)
}

@Composable
fun MovieScreen(
    @StringRes resourceId: Int,
    onMovieSelected: (String) -> Unit,
    upPress: () -> Unit,
    items: List<com.sample.tmdb.domain.model.Movie>
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(Dimens.CreditCardSize),
        contentPadding = PaddingValues(
            start = Dimens.PaddingSmall,
            end = Dimens.PaddingSmall,
            top = Dimens.PaddingSmall,
            bottom = WindowInsets.navigationBars.getBottom(LocalDensity.current)
                .toDp().dp.plus(
                    Dimens.PaddingMedium
                )
        ),
        content = {

            item(span = {
                GridItemSpan(maxLineSpan)
            }) {
                Spacer(
                    Modifier.windowInsetsTopHeight(
                        WindowInsets.statusBars.add(WindowInsets(top = 56.dp))
                    )
                )
            }

            items(items.size) { index ->
                MovieCard(person = items[index], onMovieSelected = onMovieSelected)
            }
        }
    )
    DestinationBar(title = stringResource(resourceId), upPress = upPress)
}