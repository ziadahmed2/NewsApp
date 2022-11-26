package com.itworx.search_presentation

import com.itworx.search_domain.model.Article


sealed class SearchEvent {
    data class OnQueryChange(val query: String) : SearchEvent()
    data class OnArticleClick(val article: Article) : SearchEvent()
    data class OnSearchFocusChange(val isFocused: Boolean) : SearchEvent()
    object OnSearch : SearchEvent()
}
