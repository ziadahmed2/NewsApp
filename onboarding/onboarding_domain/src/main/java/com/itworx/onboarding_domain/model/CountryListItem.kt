package com.itworx.onboarding_domain.model

import com.itworx.core.domain.model.Country

data class CountryListItem(
    val country: Country,
    val isSelected: Boolean
)
