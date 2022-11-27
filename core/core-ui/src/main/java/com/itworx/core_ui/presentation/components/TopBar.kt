package com.itworx.core_ui.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.itworx.core_ui.LocalSpacing
import java.time.LocalDate

@Composable
fun TopBar(title: String, subtitle: String? = null) {
    val spacing = LocalSpacing.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.Black)
    )

    Text(
        text = title,
        style = MaterialTheme.typography.h2,
        modifier = Modifier.padding(top = spacing.spaceMedium)
    )

    if (!subtitle.isNullOrEmpty()){
        Text(
            text = LocalDate.now().toString(),
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(bottom = spacing.spaceSmall)
        )
    }

    Box(
        modifier = Modifier
            .padding(top = spacing.spaceMedium)
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.Black)
    )
}