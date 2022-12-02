package com.itworx.headlines_data.remote

import com.itworx.core_data.remote.dto.HeadlinesDto
import com.itworx.headlines_data.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getHeadlines(
        @Query("country")
        countryCode: String,
        @Query("category")
        category: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = BuildConfig.API_KEY
    ): HeadlinesDto

    companion object {
        const val BASE_URL = "https://newsapi.org/"
    }
}