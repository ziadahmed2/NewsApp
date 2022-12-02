package com.itworx.saved_articles_presentation

import com.itworx.core_domain.model.Article


data class SavedArticlesState(
    val articles: List<Article> = emptyList(),
)
