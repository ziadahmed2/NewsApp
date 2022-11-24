package com.itworx.newsapp.navigation.nav_graph

import androidx.compose.material.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.itworx.newsapp.navigation.Route
import com.itworx.onboarding_presentation.welcome.WelcomeScreen

fun NavGraphBuilder.onboardingNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = Route.WELCOME,
        route = Route.ONBOARDING_GRAPH_ROUTE
    ) {
        composable(
            route = Route.WELCOME
        ) {
            WelcomeScreen {
                navController.navigate(Route.COUNTRY)
            }
        }
        composable(
            route = Route.COUNTRY
        ) {
            Text(text = "Country")
        }
        composable(
            route = Route.CATEGORIES
        ) {
            Text(text = "Categories")
        }
    }
}