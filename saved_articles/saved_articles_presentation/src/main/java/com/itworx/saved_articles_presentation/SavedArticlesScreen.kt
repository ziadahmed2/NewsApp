package com.itworx.saved_articles_presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.itworx.core_domain.R
import com.itworx.core_domain.model.Article
import com.itworx.core_ui.presentation.components.ArticleRow
import com.itworx.core_ui.presentation.components.TopBar

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