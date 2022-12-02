package com.itworx.headlines_domain.repo

import androidx.paging.PagingData
import com.itworx.core_domain.model.Article
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface ArticleRepo {

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getListItems(flow: MutableStateFlow<String>): Flow<PagingData<Article>>

    suspend fun insertArticle(article: Article)
    suspend fun deleteArticle(article: Article)
}