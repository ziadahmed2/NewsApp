package com.itworx.saved_articles_data

import com.itworx.core.data.local.entity.ArticleEntity
import com.itworx.saved_articles_domain.model.Article


internal fun ArticleEntity.toArticle(): Article = Article(
    title = this.title,
    urlToImage = this.urlToImage,
    source = this.source,
    date = this.publishedAt,
    description = this.description,
    url = this.url
)

