package com.itworx.article_domain.repo

import com.itworx.core_domain.model.Article
import kotlinx.coroutines.flow.Flow

interface ArticleRepo {

    fun getSavedArticles(): Flow<List<Article>>
    suspend fun insertArticle(article: Article)
    suspend fun deleteArticle(article: Article)
}