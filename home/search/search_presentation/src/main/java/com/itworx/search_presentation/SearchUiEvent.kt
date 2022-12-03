package com.itworx.search_presentation

import com.itworx.core_domain.model.Article


sealed class SearchUiEvent {
    data class OpenWebView(val article: Article): SearchUiEvent()
}