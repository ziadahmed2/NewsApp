package com.itworx.search_presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.itworx.core_ui.LocalSpacing
import com.itworx.search_domain.model.Article

@Composable
fun ArticleRow(
    article: Article,
    onClick: () -> Unit
) {
    val spacing = LocalSpacing.current
    Column(modifier = Modifier.clickable(onClick = { onClick() })) {
        Divider(
            color = MaterialTheme.colors.secondary.copy(
                alpha = 0.2f
            )
        )
        Row(
            modifier = Modifier.padding(all = spacing.spaceSmall),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RemoteImage(
                url = article.urlToImage,
                modifier = Modifier.requiredSize(100.dp)
            )
            WidthSpacer(value = spacing.spaceSmall)
            Column {
                if (!article.source.isNullOrEmpty()) {
                    Text(
                        text = article.source ?: "",
                        style = MaterialTheme.typography.body1
                    )
                    HeightSpacer(value = spacing.spaceSmall)
                }
                Text(
                    text = article.title ?: "",
                    style = MaterialTheme.typography.body1,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                HeightSpacer(value = spacing.spaceSmall)
                Text(
                    text = article.date ?: "",
                    style = MaterialTheme.typography.body1
                )
            }
        }
        HeightSpacer(value = spacing.spaceSmall)
    }
}