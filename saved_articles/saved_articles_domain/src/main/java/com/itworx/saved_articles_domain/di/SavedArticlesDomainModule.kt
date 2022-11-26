package com.itworx.saved_articles_domain.di

import com.itworx.saved_articles_domain.repo.ArticleRepo
import com.itworx.saved_articles_domain.use_cases.GetSavedArticlesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object SavedArticlesDomainModule {

    @ViewModelScoped
    @Provides
    fun provideSavedArticlesUseCase(
        repository: ArticleRepo
    ): GetSavedArticlesUseCase {
        return GetSavedArticlesUseCase(repository)
    }
}