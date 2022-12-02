package com.itworx.headlines_domain.use_cases

import androidx.paging.PagingData
import com.itworx.core_domain.model.Article
import com.itworx.headlines_domain.repo.ArticleRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


class GetArticlesUseCase @Inject constructor(
    private val articleRepo: ArticleRepo
) {

    operator fun invoke(params: MutableStateFlow<String>): Flow<PagingData<Article>> {
        return articleRepo.getListItems(params)
    }

}