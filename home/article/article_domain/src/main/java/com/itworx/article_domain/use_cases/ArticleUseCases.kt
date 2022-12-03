package com.itworx.article_domain.use_cases

data class ArticleUseCases(
    val saveArticleUseCase: SaveArticleUseCase,
    val deleteArticleUseCase: DeleteArticleUseCase,
    val getSavedArticlesUseCase: GetSavedArticlesUseCase
)
