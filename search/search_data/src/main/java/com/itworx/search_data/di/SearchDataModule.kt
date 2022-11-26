package com.itworx.search_data.di

import com.itworx.search_data.remote.NewsApi
import com.itworx.search_data.repo.ArticleRepoImpl
import com.itworx.search_domain.repo.ArticleRepo2
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchDataModule {

    @Provides
    @Singleton
    fun provideNewsApi(client: OkHttpClient): NewsApi {
        return Retrofit.Builder()
            .baseUrl(NewsApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideArticleRepo(
        api: NewsApi
    ): ArticleRepo2 {
        return ArticleRepoImpl(api = api)
    }
}