package com.itworx.onboarding_presentation.categories

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itworx.core_domain.R
import com.itworx.core_domain.preferences.Preferences
import com.itworx.core.util.UiEvent
import com.itworx.core_domain.util.UiText
import com.itworx.onboarding_domain.model.CategoryListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val preferences: Preferences,
) : ViewModel() {

    var listOfCategories by mutableStateOf(
        listOf(
            CategoryListItem("Business", false),
            CategoryListItem("Entertainment", false),
            CategoryListItem("General", false),
            CategoryListItem("Health", false),
            CategoryListItem("Science", false),
            CategoryListItem("Sports", false),
            CategoryListItem("Technology", false)
        )
    )
        private set

    var selectedCategories by mutableStateOf<List<String>?>(emptyList())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onCategorySelected(categoryListItem: CategoryListItem) {
        listOfCategories = listOfCategories.map {
            if (it == categoryListItem) {
                if (selectedCategories?.size == 3 && !categoryListItem.isSelected) {
                    viewModelScope.launch {
                        _uiEvent.send(UiEvent.ShowSnackbar(message = UiText.StringResource(R.string.error_unknown)))
                    }
                    it
                } else {
                    it.copy(isSelected = !categoryListItem.isSelected)
                }
            } else it
        }
        selectedCategories = listOfCategories.filter { it.isSelected }.map { it.category }
    }

    fun onNextClick() {
        viewModelScope.launch {
            preferences.saveCategories(selectedCategories?.map { it.lowercase() } ?: emptyList())
            _uiEvent.send(UiEvent.Success)
        }
    }
}