package com.itworx.headlines_domain.di

import com.itworx.headlines_domain.repo.ArticleRepo
import com.itworx.headlines_domain.use_cases.ArticleUsecases
import com.itworx.headlines_domain.use_cases.DeleteArticleUseCase
import com.itworx.headlines_domain.use_cases.GetArticlesUseCase
import com.itworx.headlines_domain.use_cases.SaveArticleUseCase
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
    ): ArticleUsecases {
        return ArticleUsecases(
            saveArticleUseCase = SaveArticleUseCase(repository),
            deleteArticleUseCase = DeleteArticleUseCase(repository)
        )
    }
}