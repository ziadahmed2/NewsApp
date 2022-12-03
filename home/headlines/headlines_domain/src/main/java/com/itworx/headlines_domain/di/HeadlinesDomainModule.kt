package com.itworx.headlines_domain.di

import com.itworx.headlines_domain.repo.ArticleRepo
import com.itworx.headlines_domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object HeadlinesDomainModule {

    @ViewModelScoped
    @Provides
    fun provideHeadlinesUseCases(
        repository: ArticleRepo
    ): GetArticlesUseCase {
        return GetArticlesUseCase(repository)
    }

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