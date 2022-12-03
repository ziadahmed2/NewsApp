package com.itworx.headlines_presentation.headline

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.itworx.core_domain.R
import com.itworx.core_domain.model.Article
import com.itworx.core_ui.Dimensions
import com.itworx.core_ui.LocalSpacing
import com.itworx.core_ui.presentation.components.ArticleRow
import com.itworx.core_ui.presentation.components.TopBar
import okio.IOException
import java.time.LocalDate

@Composable
fun HeadlinesScreen(
    viewModel: HeadlinesViewModel = hiltViewModel(),
    onClick: (article: Article) -> Unit
) {
    val allArticles = viewModel.getArticles.collectAsLazyPagingItems()
    val spacing = LocalSpacing.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TopBar(
            title = stringResource(id = R.string.latest_news),
            subtitle = LocalDate.now().toString()
        )
        ArticleList(
            allArticles.loadState.refresh,
            allArticles,
            spacing,
            onClick
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ArticleList(
    refreshLoadState: LoadState,
    articles: LazyPagingItems<Article>,
    spacing: Dimensions,
    onClick: (article: Article) -> Unit
) {
    when (refreshLoadState) {
        is LoadState.NotLoading -> {
            LazyColumn(modifier = Modifier.padding(bottom = 56.dp)) {
                items(articles.itemCount) { i ->
                    ArticleRow(
                        article = articles[i] ?: Article(),
                        onClick = {
                            onClick(articles[i] ?: Article())
                        }
                    )
                }
                when (articles.loadState.append) {
                    is LoadState.Loading -> item {
                        LoadingItem(
                            modifier = Modifier
                                .animateItemPlacement(animationSpec = tween(600))
                                .animateContentSize(animationSpec = tween(600)),
                            spacing
                        )
                    }
                    is LoadState.Error -> item {
                        EndOfProductsItem(
                            modifier = Modifier
                                .animateItemPlacement(animationSpec = tween(600))
                                .animateContentSize(animationSpec = tween(600))
                                .padding(bottom = 56.dp),
                            errorType = (articles.loadState.append as LoadState.Error).error,
                            spacing
                        )
                    }
                    else -> Unit
                }
            }
        }
        is LoadState.Loading -> LoadingItem(spacing = spacing, modifier = Modifier)
        is LoadState.Error -> ErrorItem(errorType = refreshLoadState.error, spacing = spacing)
        else -> Unit
    }
}


@Composable
fun ErrorItem(errorType: Throwable, spacing: Dimensions) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(spacing.spaceMedium),
            text =
            if (errorType is IOException) stringResource(
                id = R.string.connection_error
            )
            else stringResource(id = R.string.no_articles_error)
        )
    }
}

@Composable
fun LoadingItem(modifier: Modifier, spacing: Dimensions) {
    Box(modifier = modifier.fillMaxSize()) {
        CircularProgressIndicator(
            color = Color.Green,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(spacing.spaceMedium),
        )
    }
}

@Composable
fun EndOfProductsItem(modifier: Modifier, errorType: Throwable, spacing: Dimensions) {
    Box(modifier = modifier.fillMaxSize()) {
        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(spacing.spaceMedium),
            textAlign = TextAlign.Center,
            color = Color.LightGray,
            text = if (errorType is IOException) stringResource(id = R.string.connection_error)
            else stringResource(id = R.string.end_of_articles)
        )
    }
}