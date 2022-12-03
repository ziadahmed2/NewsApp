package com.itworx.search_domain.di

import com.itworx.search_domain.repo.ArticleRepo
import com.itworx.search_domain.use_cases.SearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object SearchDomainModule {

    @ViewModelScoped
    @Provides
    fun provideSearchUseCase(
        repository: ArticleRepo
    ): SearchUseCase {
        return SearchUseCase(
            articleRepo = repository
        )
    }
}