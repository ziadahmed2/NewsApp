package com.itworx.search_presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itworx.core_domain.util.Resource
import com.itworx.core_domain.util.getError
import com.itworx.search_domain.model.Article
import com.itworx.search_domain.use_cases.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    var state by mutableStateOf(SearchState())
        private set

    private val _uiEvent = Channel<SearchUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnQueryChange -> {
                state = state.copy(query = event.query)
            }
            is SearchEvent.OnSearch -> {
                executeSearch()
            }
            is SearchEvent.OnSearchFocusChange -> {
                state = state.copy(
                    isHintVisible = !event.isFocused && state.query.isBlank()
                )
            }
            is SearchEvent.OnArticleClick -> {
                openWebView(event.article)
            }
        }
    }

    @OptIn(InternalCoroutinesApi::class)
    private fun executeSearch() {
        viewModelScope.launch {
            state = state.copy(
                isSearching = true,
                articles = emptyList()
            )
            searchUseCase(state.query)
                .collectLatest { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { articles ->
                                state = state.copy(
                                    articles = articles
                                )
                            }
                        }
                        is Resource.Error -> {
                            state = state.copy(error = result.getError())
                        }
                        is Resource.Loading -> {
                            state = state.copy(isSearching = result.isLoading)
                        }
                    }
                }
        }
    }

    private fun openWebView(article: Article) {
        viewModelScope.launch {
            _uiEvent.send(SearchUiEvent.OpenWebView(article))
        }
    }
}