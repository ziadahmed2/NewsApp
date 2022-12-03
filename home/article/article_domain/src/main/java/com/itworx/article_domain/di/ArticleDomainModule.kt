package com.itworx.article_domain.di

import com.itworx.article_domain.repo.ArticleRepo
import com.itworx.article_domain.use_cases.ArticleUseCases
import com.itworx.article_domain.use_cases.DeleteArticleUseCase
import com.itworx.article_domain.use_cases.GetSavedArticlesUseCase
import com.itworx.article_domain.use_cases.SaveArticleUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ArticleDomainModule {

    @ViewModelScoped
    @Provides
    fun provideArticleUseCase(
        repository: ArticleRepo
    ): ArticleUseCases {
        return ArticleUseCases(
            saveArticleUseCase = SaveArticleUseCase(repository),
            deleteArticleUseCase = DeleteArticleUseCase(repository),
            getSavedArticlesUseCase = GetSavedArticlesUseCase(repository)
        )
    }
}