package com.itworx.article_data.di

import com.itworx.article_data.repo.ArticleRepoImpl
import com.itworx.article_domain.repo.ArticleRepo
import com.itworx.core_data.local.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ArticleDataModule {

    @Provides
    @Singleton
    fun provideArticleRepo(
        db: ArticleDatabase
    ): ArticleRepo {
        return ArticleRepoImpl(dao = db.dao)
    }
}