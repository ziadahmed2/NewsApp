package com.itworx.search_data.mappers

import com.itworx.search_data.remote.dto.ArticleDto
import com.itworx.search_domain.model.Article
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


internal fun ArticleDto.toDomain(): Article =
    Article(
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

