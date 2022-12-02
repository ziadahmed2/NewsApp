package com.itworx.search_domain.use_cases

import com.itworx.core_domain.model.Article
import com.itworx.core_domain.util.Resource
import com.itworx.search_domain.repo.ArticleRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class SearchUseCase @Inject constructor(
    private val articleRepo: ArticleRepo
) {

    suspend operator fun invoke(searchQuery: String): Flow<Resource<List<Article>>> {
        return articleRepo.getSearchResults(searchQuery)
    }
}