package com.itworx.search_domain.repo

import com.itworx.core_domain.util.Resource
import com.itworx.search_domain.model.Article
import kotlinx.coroutines.flow.Flow

interface ArticleRepo2 {

    suspend fun getListItems(query: String): Flow<Resource<List<Article>>>
}