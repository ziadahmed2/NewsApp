package com.itworx.headlines_domain.repo

import androidx.paging.PagingData
import com.itworx.core_domain.model.Article
import kotlinx.coroutines.flow.Flow

interface ArticleRepo {

    fun getListItems(): Flow<PagingData<Article>>
}