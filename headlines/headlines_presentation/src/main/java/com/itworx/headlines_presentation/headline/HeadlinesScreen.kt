package com.itworx.headlines_presentation.headline

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HeadlinesScreen(
    viewModel: HeadlinesViewModel = hiltViewModel()
) {

    Text(text = "Headlines")
}