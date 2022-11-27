package com.itworx.newsapp.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.itworx.core_ui.domain.model.BottomNavItem
import com.itworx.headlines_presentation.utils.Constants
import com.itworx.newsapp.R
import com.itworx.newsapp.navigation.nav_graph.HomeNavGraph

@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {

    val bottomNavList = listOf(
        BottomNavItem(
            stringResource(id = com.itworx.core_domain.R.string.news),
            Route.HEADLINES,
            icon = Icons.Default.Home
        ),
        BottomNavItem(
            stringResource(id = com.itworx.core_domain.R.string.saved),
            Route.SAVED_ARTICLES,
            icon = Icons.Default.Favorite
        ),
        BottomNavItem(
            stringResource(id = com.itworx.core_domain.R.string.search),
            Route.SEARCH,
            icon = Icons.Default.Search
        )
    )

    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    when (navBackStackEntry?.destination?.route) {
        Route.WEBVIEW + "/{${Constants.PARAM_ARTICLE_OBJECT}}/{${Constants.PARAM_ADD_ARTICLE}}" -> {
            bottomBarState.value = false
        }
        else -> {
            bottomBarState.value = true
        }
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = bottomNavList,
                navController = navController,
                bottomBarState = bottomBarState,
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
        Box(modifier = Modifier) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.background),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            HomeNavGraph(navController = navController)
        }
    }
}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit,
    bottomBarState: MutableState<Boolean>
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
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
                                    Text(
                                        text = it.name,
                                        textAlign = TextAlign.Center,
                                        fontSize = 12.sp
                                    )
                                }
                            }
                        }
                    )
                }
            }
        }
    )
}