package com.itworx.newsapp.navigation.nav_graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.google.gson.Gson
import com.itworx.article_presentation.webview.WebViewScreen
import com.itworx.core_ui.utils.Constants
import com.itworx.headlines_presentation.headline.HeadlinesScreen
import com.itworx.newsapp.navigation.Route
import com.itworx.saved_articles_presentation.SavedArticlesScreen
import com.itworx.search_presentation.SearchScreen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalComposeUiApi::class, ExperimentalCoilApi::class)
@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Route.HOME,
        startDestination = Route.HEADLINES
    ) {

        composable(
            route = Route.HEADLINES
        ) {
            HeadlinesScreen(
                onClick = {
                    navController.navigate(
                        "${Route.WEBVIEW}/${
                            URLEncoder.encode(
                                Gson().toJson(it),
                                StandardCharsets.UTF_8.toString()
                            )
                        }"
                    )
                }
            )
        }

        composable(
            route = Route.WEBVIEW + "/{${Constants.PARAM_ARTICLE_OBJECT}}",
            arguments = listOf(navArgument(Constants.PARAM_ARTICLE_OBJECT) {
                type = NavType.StringType
            })
        ) {
            WebViewScreen()
        }

        composable(
            route = Route.SAVED_ARTICLES
        ) {
            SavedArticlesScreen(
                onClick = {
                    navController.navigate(
                        "${Route.WEBVIEW}/${
                            URLEncoder.encode(
                                Gson().toJson(it),
                                StandardCharsets.UTF_8.toString()
                            )
                        }"
                    )
                }
            )
        }

        composable(
            route = Route.SEARCH
        ) {
            SearchScreen(onNavigate = {
                navController.navigate(
                    "${Route.WEBVIEW}/${
                        URLEncoder.encode(
                            Gson().toJson(it),
                            StandardCharsets.UTF_8.toString()
                        )
                    }"
                )
            })
        }
    }
}