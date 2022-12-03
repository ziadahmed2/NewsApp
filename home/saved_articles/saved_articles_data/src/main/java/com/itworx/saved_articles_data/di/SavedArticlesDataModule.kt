package com.itworx.saved_articles_data.di

import com.itworx.core_data.local.ArticleDatabase
import com.itworx.saved_articles_data.repo.ArticleRepoImpl
import com.itworx.saved_articles_domain.repo.ArticleRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SavedArticlesDataModule {

    @Provides
    @Singleton
    fun provideSavedArticleRepo(
        db: ArticleDatabase
    ): ArticleRepo {
        return ArticleRepoImpl(dao = db.dao)
    }
}