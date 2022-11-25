package com.itworx.headlines_data.di

import android.app.Application
import androidx.room.Room
import com.itworx.core.domain.preferences.Preferences
import com.itworx.headlines_data.local.ArticleDatabase
import com.itworx.headlines_data.remote.NewsApi
import com.itworx.headlines_data.repo.ArticleRepoImpl
import com.itworx.headlines_domain.repo.ArticleRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HeadlinesDataModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

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
    fun provideArticleDatabase(app: Application): ArticleDatabase {
        return Room.databaseBuilder(
            app,
            ArticleDatabase::class.java,
            "article_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideArticleRepo(
        api: NewsApi,
        db: ArticleDatabase,
        preferences: Preferences
    ): ArticleRepo {
        return ArticleRepoImpl(dao = db.dao, api = api, preferences = preferences)
    }
}