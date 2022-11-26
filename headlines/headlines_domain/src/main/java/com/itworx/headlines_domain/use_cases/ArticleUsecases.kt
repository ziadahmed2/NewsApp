package com.itworx.headlines_domain.use_cases

data class ArticleUsecases(
    val saveArticleUseCase: SaveArticleUseCase,
    val deleteArticleUseCase: DeleteArticleUseCase
)
