package com.itworx.article_data.repo

import com.itworx.article_domain.repo.ArticleRepo
import com.itworx.core_data.local.ArticleDao
import com.itworx.core_data.mappers.toArticle
import com.itworx.core_data.mappers.toEntity
import com.itworx.core_domain.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ArticleRepoImpl(
    private val dao: ArticleDao
) : ArticleRepo {


    override fun getSavedArticles(): Flow<List<Article>> {
        return dao.getAllArticles().map { article ->
            article.map { articleEntity ->
                articleEntity.toArticle()
            }
        }
    }

    override suspend fun insertArticle(article: Article) {
        dao.upsert(article.toEntity())
    }

    override suspend fun deleteArticle(article: Article) {
        dao.deleteArticle(article.title ?: "")
    }
}