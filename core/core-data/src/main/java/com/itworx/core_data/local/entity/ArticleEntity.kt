package com.itworx.core_data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArticleEntity(
    val description: String?,
    val publishedAt: String?,
    val source: String?,
    val title: String?,
    @PrimaryKey(autoGenerate = false)
    val url: String,
    val urlToImage: String?
)
