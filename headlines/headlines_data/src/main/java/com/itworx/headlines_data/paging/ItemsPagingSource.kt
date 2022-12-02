package com.itworx.headlines_data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.itworx.core_data.mappers.toDomain
import com.itworx.core_data.remote.dto.HeadlinesDto
import com.itworx.headlines_data.remote.NewsApi
import com.itworx.core_domain.model.Article
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class ItemsPagingSource @Inject constructor(
    private val api: NewsApi,
    private val country: String,
    private val categories: List<String>
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: FIRST_PAGE_INDEX
        val headlinesDto = HeadlinesDto()
        return try {

            for (category in categories) {
                val response = api.getHeadlines(
                    countryCode = country,
                    category = category,
                    pageNumber = page
                )
                headlinesDto.articles = headlinesDto.articles?.plus(response.articles ?: listOf())
                if (response.status != "ok") {
                    return LoadResult.Error(Throwable(message = response.message))
                } else {
                    headlinesDto.status = response.status
                    headlinesDto.message = response.message
                }
            }

            if (headlinesDto.status == "ok") {
                val articleList = headlinesDto.articles
                val articleListDomain = headlinesDto.articles?.map {
                    it.toDomain()
                }?.sortedByDescending { it.date }

                LoadResult.Page(
                    articleListDomain ?: emptyList(),
                    prevKey = if (page == FIRST_PAGE_INDEX) null else page - 1,
                    nextKey = if (articleList?.isEmpty() == true) null else page + 1
                )

            } else {
                LoadResult.Error(Throwable(message = headlinesDto.message))
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