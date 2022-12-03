package com.itworx.article_presentation.webview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.itworx.article_domain.use_cases.ArticleUseCases
import com.itworx.core.util.UiEvent
import com.itworx.core_domain.R
import com.itworx.core_domain.model.Article
import com.itworx.core_domain.util.UiText
import com.itworx.core_ui.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import javax.inject.Inject

@HiltViewModel
class WebviewViewModel @Inject constructor(
    private val articleUseCases: ArticleUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(WebviewState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    val article: Article? = Gson().fromJson(
        URLDecoder.decode(
            savedStateHandle.get(Constants.PARAM_ARTICLE_OBJECT),
            StandardCharsets.UTF_8.toString()
        ),
        Article::class.java
    )

    init {
        getSavedArticles()
    }

    private fun getSavedArticles() {
        articleUseCases.getSavedArticlesUseCase().onEach { articles ->
            state = state.copy(
                isSaved = articles.any { it.title == article?.title }
            )
        }.launchIn(viewModelScope)
    }

    fun onFabClicked() {
        when (state.isSaved) {
            true -> deleteArticle(article ?: Article())
            else -> saveArticle(article ?: Article())
        }
    }

    private fun saveArticle(article: Article) {
        viewModelScope.launch {
            articleUseCases.saveArticleUseCase(article)
            _uiEvent.send(
                UiEvent.ShowSnackbar(
                    UiText.StringResource(R.string.saved_successfully)
                )
            )
            state = state.copy(
                isSaved = true
            )
        }
    }

    private fun deleteArticle(article: Article) {
        viewModelScope.launch {
            articleUseCases.deleteArticleUseCase(article)
            _uiEvent.send(
                UiEvent.ShowSnackbar(
                    UiText.StringResource(R.string.deleted_successfully)
                )
            )
            state = state.copy(
                isSaved = false
            )
        }
    }
}