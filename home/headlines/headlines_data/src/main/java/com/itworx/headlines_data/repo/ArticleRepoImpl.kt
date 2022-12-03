package com.itworx.headlines_data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.itworx.core_domain.model.Article
import com.itworx.core_domain.preferences.Preferences
import com.itworx.headlines_data.paging.ItemsPagingSource
import com.itworx.headlines_data.remote.NewsApi
import com.itworx.headlines_domain.repo.ArticleRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class ArticleRepoImpl(
    private val api: NewsApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    preferences: Preferences
) : ArticleRepo {

    private val pref = preferences.loadUserInfo()

    override fun getListItems(): Flow<PagingData<Article>> =
        Pager(
            config = PagingConfig(pageSize = 5),
            pagingSourceFactory = {
                ItemsPagingSource(
                    api,
                    pref.country.code,
                    pref.categories,
                    dispatcher = dispatcher
                )
            },
        ).flow
}