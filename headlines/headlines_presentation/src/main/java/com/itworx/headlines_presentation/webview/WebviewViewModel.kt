package com.itworx.headlines_presentation.webview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itworx.core.util.UiEvent
import com.itworx.core.util.UiText
import com.itworx.core.R
import com.itworx.headlines_domain.model.Article
import com.itworx.headlines_domain.use_cases.ArticleUsecases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WebviewViewModel @Inject constructor(
    private val articleUseCases: ArticleUsecases
) : ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun saveArticle(article: Article, addArticle: Boolean) {
        viewModelScope.launch {
            if (addArticle){
                articleUseCases.saveArticleUseCase(article)
                _uiEvent.send(
                    UiEvent.ShowSnackbar(
                        UiText.StringResource(R.string.saved_successfully)
                    )
                )
            } else {
                articleUseCases.deleteArticleUseCase(article)
                _uiEvent.send(
                    UiEvent.ShowSnackbar(
                        UiText.StringResource(R.string.deleted_successfully)
                    )
                )
            }
        }
    }

}