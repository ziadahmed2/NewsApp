package com.itworx.headlines_presentation.headline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.itworx.core_domain.preferences.Preferences
import com.itworx.headlines_domain.use_cases.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HeadlinesViewModel @Inject constructor(
    getArticlesUseCase: GetArticlesUseCase,
    preferences: Preferences
) : ViewModel() {

    init {
        preferences.saveShouldShowOnboarding(false)
    }

    val getArticles = getArticlesUseCase().cachedIn(viewModelScope)
}