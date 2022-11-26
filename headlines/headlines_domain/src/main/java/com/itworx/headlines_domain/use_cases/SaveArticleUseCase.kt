package com.itworx.headlines_domain.use_cases

import com.itworx.headlines_domain.model.Article
import com.itworx.headlines_domain.repo.ArticleRepo
import javax.inject.Inject


class SaveArticleUseCase @Inject constructor(
    private val articleRepo: ArticleRepo
) {

    suspend operator fun invoke(article: Article) {
        return articleRepo.insertArticle(article)
    }
}