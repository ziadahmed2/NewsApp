package com.itworx.onboarding_presentation.categories

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.itworx.core_domain.R
import com.itworx.core.util.UiEvent
import com.itworx.core_ui.Dimensions
import com.itworx.core_ui.LocalSpacing
import com.itworx.onboarding_domain.model.CategoryListItem
import com.itworx.onboarding_presentation.components.ActionButton

@Composable
fun CategoriesScreen(
    onNextClick: () -> Unit,
    scaffoldState: ScaffoldState,
    viewModel: CategoriesViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> onNextClick()
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }
                else -> Unit
            }
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            CategoriesHeader(spacing)
        }
        items(viewModel.listOfCategories.size) { i ->
            CategoryItem(
                spacing = spacing,
                categoryListItem = viewModel.listOfCategories[i],
                onCountrySelect = {
                    viewModel.onCategorySelected(viewModel.listOfCategories[i])
                })
        }
        item {
            ActionButton(
                text = stringResource(id = R.string.next),
                onClick = { viewModel.onNextClick() },
                isEnabled = viewModel.selectedCategories?.size == 3,
                modifier = Modifier.padding(top = spacing.spaceSmall)
            )
        }
    }
}

@Composable
fun CategoriesHeader(spacing: Dimensions) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(spacing.spaceMedium),
        text = stringResource(R.string.select_fav_categories),
        style = MaterialTheme.typography.h3
    )
}

@Composable
fun CategoryItem(
    spacing: Dimensions,
    categoryListItem: CategoryListItem,
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
        Text(categoryListItem.category)
        if (categoryListItem.isSelected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Selected",
                tint = Color.Green,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}