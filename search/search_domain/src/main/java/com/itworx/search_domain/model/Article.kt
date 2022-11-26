package com.itworx.search_domain.model

data class Article(
    val description: String? = "",
    val date: String? = "",
    val source: String? = "",
    val title: String? = "",
    val url: String? = "",
    val urlToImage: String? = ""
)
