package com.itworx.article_domain.use_cases

import com.itworx.article_domain.repo.ArticleRepo
import com.itworx.core_domain.model.Article
import javax.inject.Inject


class SaveArticleUseCase @Inject constructor(
    private val articleRepo: ArticleRepo
) {

    suspend operator fun invoke(article: Article) {
        return articleRepo.insertArticle(article)
    }
}