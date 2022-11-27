package com.itworx.saved_articles_presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.itworx.core_domain.R
import com.itworx.core_ui.LocalSpacing
import com.itworx.core_ui.presentation.components.TopBar
import com.itworx.saved_articles_domain.model.Article
import com.itworx.saved_articles_presentation.components.HeightSpacer
import com.itworx.saved_articles_presentation.components.RemoteImage
import com.itworx.saved_articles_presentation.components.WidthSpacer

@Composable
fun SavedArticlesScreen(
    viewModel: SavedArticlesViewModel = hiltViewModel(),
    onClick: (article: Article) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TopBar(title = stringResource(id = R.string.saved_news))
        ArticleList(
            viewModel.state.articles,
            onClick
        )
    }
}

@Composable
fun ArticleList(articles: List<Article>, onClick: (article: Article) -> Unit) {
    if (articles.isEmpty()) Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = stringResource(id = R.string.no_articles_error),
            modifier = Modifier.align(Alignment.Center)
        )
    }
    else {
        LazyColumn(modifier = Modifier.padding(bottom = 56.dp)) {
            items(articles.size) { i ->
                ArticleRow(
                    article = articles[i],
                    onClick = {
                        onClick(articles[i])
                    }
                )
            }
        }
    }
}

@Composable
fun ArticleRow(
    article: Article,
    onClick: () -> Unit
) {
    val spacing = LocalSpacing.current
    Column(modifier = Modifier.clickable(onClick = { onClick() })) {
        Divider(
            color = MaterialTheme.colors.secondary.copy(
                alpha = 0.2f
            )
        )
        Row(
            modifier = Modifier.padding(all = spacing.spaceSmall),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RemoteImage(
                url = article.urlToImage,
                modifier = Modifier.requiredSize(100.dp)
            )
            WidthSpacer(value = spacing.spaceSmall)
            Column {
                if (!article.source.isNullOrEmpty()) {
                    Text(
                        text = article.source ?: "",
                        style = MaterialTheme.typography.body1
                    )
                    HeightSpacer(value = spacing.spaceSmall)
                }
                Text(
                    text = article.title ?: "",
                    style = MaterialTheme.typography.body1,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                HeightSpacer(value = spacing.spaceSmall)
                Text(
                    text = article.date ?: "",
                    style = MaterialTheme.typography.body1
                )
            }
        }
        HeightSpacer(value = spacing.spaceSmall)
    }
}