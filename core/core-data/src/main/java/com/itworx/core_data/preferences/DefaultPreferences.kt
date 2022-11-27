package com.itworx.core_data.preferences

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.itworx.core_domain.model.Country
import com.itworx.core_domain.model.UserInfo
import com.itworx.core_domain.preferences.Preferences

class DefaultPreferences(
    private val sharedPref: SharedPreferences
) : Preferences {

    override fun saveCategories(categories: List<String>) {
        val gson = Gson()
        val json = gson.toJson(categories)
        sharedPref.edit()
            .putString(Preferences.KEY_CATEGORIES, json)
            .apply()
    }

    override fun saveCountry(country: Country) {
        val gson = Gson()
        val json = gson.toJson(country)
        sharedPref.edit()
            .putString(Preferences.KEY_COUNTRY, json)
            .apply()
    }

    override fun loadUserInfo(): UserInfo {
        val country = sharedPref.getString(Preferences.KEY_COUNTRY, "")
        val categories = sharedPref.getString(Preferences.KEY_CATEGORIES, "")

        return UserInfo(
            country = countryFromString(country ?: ""),
            categories = listFromString(categories ?: "")
        )
    }

    override fun saveShouldShowOnboarding(shouldShow: Boolean) {
        sharedPref.edit()
            .putBoolean(Preferences.KEY_SHOULD_SHOW_ONBOARDING, shouldShow)
            .apply()
    }

    override fun loadShouldShowOnboarding(): Boolean {
        return sharedPref.getBoolean(
            Preferences.KEY_SHOULD_SHOW_ONBOARDING,
            true
        )
    }

    private fun listFromString(list: String): ArrayList<String> {
        val gson = Gson()
        val type = object : TypeToken<ArrayList<String>>() {}.type
        return gson.fromJson(list, type)
    }

    private fun countryFromString(str: String): Country {
        val gson = Gson()
        return gson.fromJson(str, Country::class.java)
    }
}