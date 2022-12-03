package com.itworx.saved_articles_domain.repo

import com.itworx.core_domain.model.Article
import kotlinx.coroutines.flow.Flow

interface ArticleRepo {


    fun getSavedArticles(): Flow<List<Article>>
}