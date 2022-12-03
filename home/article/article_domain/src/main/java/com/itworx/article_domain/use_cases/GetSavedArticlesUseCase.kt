package com.itworx.article_domain.use_cases

import com.itworx.article_domain.repo.ArticleRepo
import com.itworx.core_domain.model.Article
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetSavedArticlesUseCase @Inject constructor(
    private val articleRepo: ArticleRepo
) {

    operator fun invoke(): Flow<List<Article>> {
        return articleRepo.getSavedArticles()
    }
}