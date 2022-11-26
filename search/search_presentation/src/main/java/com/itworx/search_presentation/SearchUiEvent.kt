package com.itworx.search_presentation

import com.itworx.search_domain.model.Article


sealed class SearchUiEvent {
    data class OpenWebView(val article: Article): SearchUiEvent()
}