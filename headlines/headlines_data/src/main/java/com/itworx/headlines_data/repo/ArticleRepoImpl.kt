package com.itworx.headlines_data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.itworx.core.domain.preferences.Preferences
import com.itworx.headlines_data.local.ArticleDao
import com.itworx.headlines_data.paging.PagingSourceCleanImpl
import com.itworx.headlines_data.remote.NewsApi
import com.itworx.headlines_data.remote.mappers.toEntity
import com.itworx.headlines_domain.model.Article
import com.itworx.headlines_domain.repo.ArticleRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

class ArticleRepoImpl(
    private val dao: ArticleDao,
    private val api: NewsApi,
    preferences: Preferences
) : ArticleRepo {

    private val pref = preferences.loadUserInfo()

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getListItems(flow: MutableStateFlow<String>): Flow<PagingData<Article>> =
        flow.flatMapLatest {
            Pager(
                config = PagingConfig(pageSize = 20),
                pagingSourceFactory = {
                    PagingSourceCleanImpl(
                        api,
                        pref.country.code,
                        pref.categories[0]
                    )
                },
            ).flow
        }

    override suspend fun insertArticle(article: Article) {
        dao.upsert(article.toEntity())
    }
}