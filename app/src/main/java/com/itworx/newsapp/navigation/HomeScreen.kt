package com.itworx.newsapp.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.itworx.core_ui.domain.model.BottomNavItem
import com.itworx.newsapp.navigation.nav_graph.HomeNavGraph

@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {

    val bottomNavList = listOf(
        BottomNavItem(
            "Home",
            Route.HEADLINES,
            icon = Icons.Default.Home
        ),
        BottomNavItem(
            "Saved",
            Route.SAVED_ARTICLES,
            icon = Icons.Default.Favorite
        ),
        BottomNavItem(
            "Search",
            Route.SEARCH,
            icon = Icons.Default.Search
        )
    )

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = bottomNavList,
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) {
        HomeNavGraph(navController = navController)
    }
}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.DarkGray,
        elevation = 5.dp
    ) {
        items.forEach {
            val selected = it.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(it) },
                selectedContentColor = Color.Green,
                unselectedContentColor = Color.Gray,
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        if (it.badgeCount > 0) {
                            BadgedBox(content = {
                                Icon(imageVector = it.icon, contentDescription = it.name)
                            }, badge = {
                                Text(text = it.badgeCount.toString())
                            })
                        } else {
                            Icon(imageVector = it.icon, contentDescription = it.name)
                        }
                        if (selected) {
                            Text(text = it.name, textAlign = TextAlign.Center, fontSize = 10.sp)
                        }
                    }
                }
            )
        }
    }
}