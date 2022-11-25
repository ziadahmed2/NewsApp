package com.itworx.headlines_presentation.headline

import androidx.lifecycle.ViewModel
import com.itworx.core.domain.preferences.Preferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HeadlinesViewModel @Inject constructor(
    private val preferences: Preferences,
) : ViewModel() {
}