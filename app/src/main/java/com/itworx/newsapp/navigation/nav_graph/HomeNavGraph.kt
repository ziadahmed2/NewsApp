package com.itworx.newsapp.navigation.nav_graph

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.itworx.newsapp.navigation.Route

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
            Text(text = "Headlines")
        }
        composable(
            route = Route.SAVED_ARTICLES
        ) {
            Text(text = "Saved")
        }
        composable(
            route = Route.SEARCH
        ) {
            Text(text = "Search")
        }
    }
}