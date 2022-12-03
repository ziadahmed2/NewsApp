package com.itworx.saved_articles_data.repo

import com.itworx.core_data.local.ArticleDao
import com.itworx.core_data.mappers.toArticle
import com.itworx.core_domain.model.Article
import com.itworx.saved_articles_domain.repo.ArticleRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ArticleRepoImpl(
    private val dao: ArticleDao
) : ArticleRepo {

    override fun getSavedArticles(): Flow<List<Article>> {
        return dao.getAllArticles().map { entity ->
            entity.map {
                it.toArticle()
            }
        }
    }
}