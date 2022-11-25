package com.itworx.headlines_data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val description: String?,
    val publishedAt: String?,
    val source: String?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)
