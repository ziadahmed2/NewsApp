package com.itworx.newsapp.navigation.nav_graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.itworx.newsapp.navigation.HomeScreen
import com.itworx.newsapp.navigation.Route
import com.itworx.newsapp.navigation.Route.ONBOARDING_GRAPH_ROUTE
import com.itworx.newsapp.navigation.Route.ROOT_GRAPH_ROUTE

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = ONBOARDING_GRAPH_ROUTE,
        route = ROOT_GRAPH_ROUTE
    ) {
        onboardingNavGraph(navController = navController)
        composable(route = Route.HOME) {
            HomeScreen()
        }
    }
}