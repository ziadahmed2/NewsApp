package com.itworx.headlines_data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.itworx.core_data.local.ArticleDao
import com.itworx.core_data.mappers.toEntity
import com.itworx.core_domain.preferences.Preferences
import com.itworx.headlines_data.paging.ItemsPagingSource
import com.itworx.headlines_data.remote.NewsApi
import com.itworx.core_domain.model.Article
import com.itworx.headlines_domain.repo.ArticleRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

class ArticleRepoImpl(
    private val dao: ArticleDao,
    private val api: NewsApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    preferences: Preferences
) : ArticleRepo {

    private val pref = preferences.loadUserInfo()

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getListItems(flow: MutableStateFlow<String>): Flow<PagingData<Article>> =
        flow.flatMapLatest {
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

    override suspend fun insertArticle(article: Article) {
        dao.upsert(article.toEntity())
    }

    override suspend fun deleteArticle(article: Article) {
        dao.deleteArticle(article.title ?: "")
    }
}