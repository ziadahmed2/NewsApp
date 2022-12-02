package com.itworx.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.rememberNavController
import com.itworx.core_domain.preferences.Preferences
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
                    Box(modifier = Modifier) {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            painter = painterResource(id = R.drawable.background),
                            contentScale = ContentScale.Crop,
                            contentDescription = null
                        )

                        SetupNavGraph(
                            navController = navController,
                            scaffoldState,
                            showOnBoarding = preferences.loadShouldShowOnboarding()
                        )
                    }
                }
            }
        }
    }
}