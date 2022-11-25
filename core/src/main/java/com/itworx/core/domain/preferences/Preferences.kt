package com.itworx.core.domain.preferences

import com.itworx.core.domain.model.Country
import com.itworx.core.domain.model.UserInfo


interface Preferences {
    fun saveCountry(country: Country)
    fun saveCategories(categories: List<String>)

    fun loadUserInfo(): UserInfo

    fun saveShouldShowOnboarding(shouldShow: Boolean)
    fun loadShouldShowOnboarding(): Boolean

    companion object {
        const val KEY_COUNTRY = "country"
        const val KEY_CATEGORIES = "categories"
        const val KEY_SHOULD_SHOW_ONBOARDING = "should_show_onboarding"
    }
}