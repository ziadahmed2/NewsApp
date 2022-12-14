package com.itworx.search_presentation

import com.itworx.core_domain.model.Article
import com.itworx.core_domain.util.UiText


data class SearchState(
    val query: String = "",
    val isHintVisible: Boolean = false,
    val isSearching: Boolean = false,
    val error: UiText = UiText.DynamicString(""),
    val articles: List<Article> = emptyList()
)