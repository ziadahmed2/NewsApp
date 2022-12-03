package com.itworx.search_data.repo

import com.itworx.core_domain.model.Article
import com.itworx.core_domain.util.Resource
import com.itworx.core_data.mappers.toDomain
import com.itworx.search_data.remote.NewsApi
import com.itworx.search_domain.repo.ArticleRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class ArticleRepoImpl(
    private val api: NewsApi
) : ArticleRepo {


    override suspend fun getSearchResults(query: String): Flow<Resource<List<Article>>> {
        return flow<Resource<List<Article>>> {
            emit(Resource.Loading(true))
            val response = try {
                val response = api.getEveryThing(query)
                response
            } catch (e: IOException) {
                e.printStackTrace()
                emit(
                    Resource.Error(
                        message = e.localizedMessage ?: "",
                        isConnectionError = true
                    )
                )
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(
                    Resource.Error(
                        message = e.localizedMessage ?: "",
                        isConnectionError = false
                    )
                )
                null
            }

            response?.let { searchResults ->
                emit(
                    Resource.Success(
                        data = searchResults.articles?.map { it.toDomain() }
                    )
                )
            }
            emit(Resource.Loading(false))
        }
    }
}