package com.itworx.headlines_data.paging

import androidx.paging.*
import com.itworx.headlines_data.remote.NewsApi
import com.itworx.headlines_data.remote.mappers.toDomain
import com.itworx.headlines_domain.model.Article
import kotlinx.coroutines.delay
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class PagingSourceCleanImpl @Inject constructor(
    private val api: NewsApi,
    private val country: String,
    private val category: String
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: FIRST_PAGE_INDEX
        return try {
            delay(500)

            val response = api.getHeadlines(
                countryCode = country,
                category = category,
                pageNumber = page
            )

            if (response.status == "ok") {
                val articleList = response.articles
                val articleListDomain = response.articles?.map { it.toDomain() }

                LoadResult.Page(
                    articleListDomain ?: emptyList(),
                    prevKey = if (page == FIRST_PAGE_INDEX) null else page - 1,
                    nextKey = if (articleList?.isEmpty() == true) null else page + 1
                )

            } else {
                LoadResult.Error(Throwable(message = response.message))
            }
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }
}