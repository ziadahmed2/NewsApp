package com.itworx.onboarding_presentation.country

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itworx.core.domain.model.Country
import com.itworx.core.domain.preferences.Preferences
import com.itworx.core.util.UiEvent
import com.itworx.onboarding_domain.model.CountryListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val preferences: Preferences,
) : ViewModel() {

    var listOfCountries by mutableStateOf(
        listOf(
            CountryListItem(Country("Egypt", "eg"), false),
            CountryListItem(Country("USA", "us"), false),
            CountryListItem(Country("France", "fr"), false)
        )
    )
        private set

    var selectedCountry by mutableStateOf<Country?>(null)
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onCountrySelect(country: CountryListItem) {
        listOfCountries = listOfCountries.map {
            if (it == country) {
                it.copy(isSelected = !country.isSelected)
            } else it.copy(isSelected = false)
        }
        selectedCountry = listOfCountries.find { it.isSelected }?.country
    }

    fun onNextClick() {
        viewModelScope.launch {
            preferences.saveCountry(selectedCountry ?: Country("Egypt", "eg"))
            _uiEvent.send(UiEvent.Success)
        }
    }
}