package com.itworx.newsapp.navigation

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.itworx.newsapp.navigation.nav_graph.HomeNavGraph

@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
    ) {
        HomeNavGraph(navController = navController)
    }
}