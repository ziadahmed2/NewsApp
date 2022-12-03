package com.itworx.saved_articles_presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itworx.saved_articles_domain.use_cases.GetSavedArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SavedArticlesViewModel @Inject constructor(
    getSavedArticlesUseCase: GetSavedArticlesUseCase
) : ViewModel() {

    var state by mutableStateOf(SavedArticlesState())
        private set

    init {
        getSavedArticlesUseCase().onEach {
            state = state.copy(
                articles = it.reversed()
            )
        }.launchIn(viewModelScope)
    }
}