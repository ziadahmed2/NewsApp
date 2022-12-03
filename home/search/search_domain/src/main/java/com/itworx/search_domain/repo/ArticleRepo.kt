package com.itworx.search_domain.repo

import com.itworx.core_domain.model.Article
import com.itworx.core_domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface ArticleRepo {

    suspend fun getSearchResults(query: String): Flow<Resource<List<Article>>>
}