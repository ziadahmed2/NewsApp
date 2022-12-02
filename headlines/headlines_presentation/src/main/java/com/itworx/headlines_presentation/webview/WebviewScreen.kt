package com.itworx.headlines_presentation.webview

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.itworx.core.util.UiEvent
import com.itworx.core_ui.LocalSpacing

@Composable
fun WebViewScreen(
    viewModel: WebviewViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val spacing = LocalSpacing.current
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }
                else -> Unit
            }
        }
    }

    Scaffold(scaffoldState = scaffoldState) {
        Box(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.fillMaxSize()) {
                AndroidView(modifier = Modifier.fillMaxSize(), factory = {
                    WebView(context).apply {
                        webViewClient = WebViewClient()
                        loadUrl(viewModel.article?.url ?: "")
                    }
                })
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.BottomCenter)
            ) {
                FloatingActionButton(
                    onClick = { viewModel.onFabClicked() },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(spacing.spaceMedium)
                ) {
                    Icon(
                        imageVector = if (viewModel.state.isSaved) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Save"
                    )
                }
            }

        }
    }
}