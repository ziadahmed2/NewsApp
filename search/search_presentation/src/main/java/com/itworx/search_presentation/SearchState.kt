package com.itworx.search_presentation

import com.itworx.core.util.UiText
import com.itworx.search_domain.model.Article


data class SearchState(
    val query: String = "",
    val isHintVisible: Boolean = false,
    val isSearching: Boolean = false,
    val error: UiText = UiText.DynamicString(""),
    val articles: List<Article> = emptyList()
)