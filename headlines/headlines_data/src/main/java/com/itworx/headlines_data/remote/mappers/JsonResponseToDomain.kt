package com.itworx.headlines_data.remote.mappers

import com.itworx.headlines_data.local.entity.ArticleEntity
import com.itworx.headlines_data.remote.dto.ArticleDto
import com.itworx.headlines_domain.model.Article


internal fun ArticleDto.toDomain(): Article = Article(
    title = this.title,
    urlToImage = this.urlToImage,
    source = this.source?.name,
    date = this.publishedAt,
    description = this.description,
    url = this.url
)

internal fun Article.toEntity(): ArticleEntity = ArticleEntity(
    title = this.title,
    urlToImage = this.urlToImage,
    source = this.source,
    publishedAt = this.date,
    description = this.description,
    url = this.url,

)

