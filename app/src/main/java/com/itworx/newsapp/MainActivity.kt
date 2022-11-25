package com.itworx.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.itworx.core.domain.preferences.Preferences
import com.itworx.newsapp.navigation.nav_graph.SetupNavGraph
import com.itworx.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold(scaffoldState = scaffoldState, modifier = Modifier.fillMaxSize()) {
                    SetupNavGraph(navController = navController, scaffoldState, showOnBoarding = preferences.loadShouldShowOnboarding())
                }
            }
        }
    }
}