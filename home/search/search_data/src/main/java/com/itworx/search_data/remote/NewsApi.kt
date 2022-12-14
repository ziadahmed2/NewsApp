package com.itworx.search_data.remote

import com.itworx.core_domain.BuildConfig
import com.itworx.core_data.remote.dto.HeadlinesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/everything")
    suspend fun getEveryThing(
        @Query("q")
        query: String,
        @Query("apiKey")
        apiKey: String = BuildConfig.API_KEY
    ): HeadlinesDto

    companion object {
        const val BASE_URL = "https://newsapi.org/"
    }
}