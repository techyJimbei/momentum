package com.example.momentum_app

import AppTheme
import SignUpViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.momentum_app.model.Task
import com.example.momentum_app.view.mainscreen.MainScreen
import com.example.momentum_app.view.onboardinggetstarted.OnboardingGetStarted
import com.example.momentum_app.view.onboardingscreen.OnboardingScreen
import com.example.momentum_app.view.sharingtask.SharePostScreen
import com.example.momentum_app.view.signinpage.SignInPage
import com.example.momentum_app.view.signuppage.SignUpPage
import com.example.momentum_app.view.splashscreen.SplashScreen
import com.example.momentum_app.view.tasklistscreen.TaskListScreen
import com.example.momentum_app.viewmodel.PostViewModel
import com.example.momentum_app.viewmodel.TaskListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val signUpViewModel = ViewModelProvider(this)[SignUpViewModel::class.java]
        val taskListViewModel = ViewModelProvider(this)[TaskListViewModel::class.java]
        val postViewModel = ViewModelProvider(this)[PostViewModel::class.java]


        enableEdgeToEdge()
        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = AppTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "SplashScreen") {
                        composable("SplashScreen") {
                            SplashScreen(
                                navController = navController,
                                context = this@MainActivity
                            )
                        }
                        composable("OnboardingGetStarted") {
                            OnboardingGetStarted(
                                navController = navController,
                                context = this@MainActivity
                            )
                        }
                        composable("OnboardingScreen") {
                            OnboardingScreen(
                                navController = navController,
                                context = this@MainActivity
                            )
                        }
                        composable("SignUpPage") {
                            SignUpPage(
                                navController = navController,
                                context = this@MainActivity,
                                viewModel = signUpViewModel
                            )
                        }
                        composable("SignInPage") {
                            SignInPage(
                                navController = navController,
                                context = this@MainActivity,
                                viewModel = signUpViewModel
                            )
                        }
                        composable("MainScreen") {
                            MainScreen(
                                navController = navController,
                                context = this@MainActivity
                            )
                        }
                        composable("TaskListScreen") {
                            TaskListScreen(
                                viewModel = taskListViewModel,
                                context = this@MainActivity,
                                modifier = Modifier,
                                navController = navController
                            )
                        }

                        composable(
                            route = "SharePostScreen/{title}/{description}",
                            arguments = listOf(
                                navArgument("title") { type = NavType.StringType },
                                navArgument("description") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val title = backStackEntry.arguments?.getString("title") ?: ""
                            val description = backStackEntry.arguments?.getString("description") ?: ""

                            SharePostScreen(
                                viewModel = postViewModel,
                                navController = navController,
                                taskTitle = title,
                                taskDescription = description,
                                context = this@MainActivity
                            )
                        }
                    }
                }
            }
        }
    }
}
