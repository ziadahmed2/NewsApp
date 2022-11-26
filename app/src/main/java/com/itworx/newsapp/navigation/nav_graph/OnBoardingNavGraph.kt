package com.itworx.newsapp.navigation.nav_graph

import androidx.compose.material.ScaffoldState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.itworx.newsapp.navigation.Route
import com.itworx.onboarding_presentation.categories.CategoriesScreen
import com.itworx.onboarding_presentation.country.CountryScreen
import com.itworx.onboarding_presentation.welcome.WelcomeScreen

fun NavGraphBuilder.onboardingNavGraph(
    navController: NavHostController,
    scaffoldState: ScaffoldState
) {
    navigation(
        startDestination = Route.WELCOME,
        route = Route.ONBOARDING_GRAPH_ROUTE
    ) {

        composable(route = Route.WELCOME) {
            WelcomeScreen {
                navController.navigate(Route.COUNTRY)
            }
        }

        composable(route = Route.COUNTRY) {
            CountryScreen(
                onNextClick = { navController.navigate(Route.CATEGORIES) }
            )
        }

        composable(route = Route.CATEGORIES) {
            CategoriesScreen(onNextClick = {
                navController.navigate(Route.HOME) {
                    popUpTo(Route.WELCOME) {
                        inclusive = true
                    }
                }
            }, scaffoldState = scaffoldState)
        }
    }
}