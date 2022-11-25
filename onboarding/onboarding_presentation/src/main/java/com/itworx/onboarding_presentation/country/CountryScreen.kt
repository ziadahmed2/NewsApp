package com.itworx.onboarding_presentation.country

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.itworx.core.R
import com.itworx.core.util.UiEvent
import com.itworx.core_ui.Dimensions
import com.itworx.core_ui.LocalSpacing
import com.itworx.onboarding_domain.model.CountryListItem
import com.itworx.onboarding_presentation.components.ActionButton
import kotlinx.coroutines.flow.collect

@Composable
fun CountryScreen(
    onNextClick: () -> Unit,
    viewModel: CountryViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> onNextClick()
                else -> Unit
            }
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            CountryHeader(spacing)
        }
        items(viewModel.listOfCountries.size) { i ->
            CountryItem(
                spacing = spacing,
                countryItem = viewModel.listOfCountries[i],
                onCountrySelect = {
                    viewModel.onCountrySelect(viewModel.listOfCountries[i])
                })
        }
        item {
            ActionButton(
                text = stringResource(id = R.string.next),
                onClick = { viewModel.onNextClick() },
                isEnabled = viewModel.selectedCountry != null,
                modifier = Modifier.padding(top = spacing.spaceSmall)
            )
        }
    }
}

@Composable
fun CountryHeader(spacing: Dimensions) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(spacing.spaceMedium),
        text = stringResource(R.string.select_fav_country),
        style = MaterialTheme.typography.h3
    )
}

@Composable
fun CountryItem(
    spacing: Dimensions,
    countryItem: CountryListItem,
    onCountrySelect: () -> Unit,
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onCountrySelect()
        }
        .padding(spacing.spaceMedium),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(countryItem.country.name)
        if (countryItem.isSelected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Selected",
                tint = Color.Green,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}