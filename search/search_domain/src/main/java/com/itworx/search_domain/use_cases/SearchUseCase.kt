package com.itworx.search_domain.use_cases

import com.itworx.core.util.Resource
import com.itworx.search_domain.model.Article
import com.itworx.search_domain.repo.ArticleRepo2
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class SearchUseCase @Inject constructor(
    private val articleRepo: ArticleRepo2
) {

    suspend operator fun invoke(searchQuery: String): Flow<Resource<List<Article>>> {
        return articleRepo.getListItems(searchQuery)
    }
}