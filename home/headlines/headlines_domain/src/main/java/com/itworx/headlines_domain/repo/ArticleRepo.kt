package com.itworx.headlines_domain.repo

import androidx.paging.PagingData
import com.itworx.core_domain.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface ArticleRepo {

    fun getListItems(flow: MutableStateFlow<String>): Flow<PagingData<Article>>
    fun getSavedArticles(): Flow<List<Article>>
    suspend fun insertArticle(article: Article)
    suspend fun deleteArticle(article: Article)
}