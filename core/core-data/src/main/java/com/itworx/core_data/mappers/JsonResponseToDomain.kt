package com.itworx.core_data.mappers

import com.itworx.core_data.local.entity.ArticleEntity
import com.itworx.core_data.remote.dto.ArticleDto
import com.itworx.core_domain.model.Article
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


fun ArticleDto.toDomain(): Article = Article(
    title = this.title,
    urlToImage = this.urlToImage,
    source = this.source?.name,
    date = LocalDateTime.parse(
        this.publishedAt,
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
    ).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).toString(),
    description = this.description,
    url = this.url
)

fun ArticleEntity.toArticle(): Article = Article(
    title = this.title,
    urlToImage = this.urlToImage,
    source = this.source,
    date = this.publishedAt,
    description = this.description,
    url = this.url
)

fun Article.toEntity(): ArticleEntity = ArticleEntity(
    title = this.title,
    urlToImage = this.urlToImage,
    source = this.source,
    publishedAt = this.date,
    description = this.description,
    url = this.url ?: ""
)

