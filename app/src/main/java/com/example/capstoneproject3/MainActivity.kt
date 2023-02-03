package com.example.capstoneproject3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.capstoneproject3.theme.CapstoneProject3Theme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import com.example.capstoneproject3.navigation.NavGraph
import com.example.capstoneproject3.navigation.Screen.MainScreen
import com.example.capstoneproject3.presentation.auth.AuthViewModel

@AndroidEntryPoint
@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private val viewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CapstoneProject3Theme {
                // A surface container using the 'background' color from the theme //
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background //para san to?
                ) {
                    navController = rememberAnimatedNavController()
                    NavGraph(
                        navController = navController
                    )
                    checkAuthState()
                }
            }
        }
    }

    private fun checkAuthState() {
        if(viewModel.isAuthenticated) {
            navigateToHomeScreen()
        }
    }

    private fun navigateToHomeScreen() = navController.navigate(MainScreen.route)
}

