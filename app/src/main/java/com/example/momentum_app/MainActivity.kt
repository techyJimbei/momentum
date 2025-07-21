package com.example.momentum_app

import AppTheme
import com.example.momentum_app.viewmodel.SignUpViewModel
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
import com.example.momentum_app.api.RetrofitInstance
import com.example.momentum_app.view.mainscreen.MainScreen
import com.example.momentum_app.view.onboardinggetstarted.OnboardingGetStarted
import com.example.momentum_app.view.onboardingscreen.OnboardingScreen
import com.example.momentum_app.view.profilescreen.ProfileScreen
import com.example.momentum_app.view.profilescreen.ProfileScreenPostPreview
import com.example.momentum_app.view.sharingtask.SharePostScreen
import com.example.momentum_app.view.sharingtask.ShareStoryScreen
import com.example.momentum_app.view.signinpage.SignInPage
import com.example.momentum_app.view.signuppage.SignUpPage
import com.example.momentum_app.view.splashscreen.SplashScreen
import com.example.momentum_app.view.storyscreen.StoryFullScreen
import com.example.momentum_app.view.tasklistscreen.TaskListScreen
import com.example.momentum_app.viewmodel.PostViewModel
import com.example.momentum_app.viewmodel.StoryViewModel
import com.example.momentum_app.viewmodel.TaskListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RetrofitInstance.init(applicationContext)


        val signUpViewModel = ViewModelProvider(this)[SignUpViewModel::class.java]
        val taskListViewModel = ViewModelProvider(this)[TaskListViewModel::class.java]
        val postViewModel = ViewModelProvider(this)[PostViewModel::class.java]
        val storyViewModel = ViewModelProvider(this)[StoryViewModel::class.java]

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
                                navController = navController, context = this@MainActivity, viewModel = signUpViewModel
                            )
                        }
                        composable("OnboardingGetStarted") {
                            OnboardingGetStarted(navController = navController, context = this@MainActivity)
                        }
                        composable("OnboardingScreen") {
                            OnboardingScreen(navController = navController, context = this@MainActivity)
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
                                postViewModel = postViewModel,
                                context = this@MainActivity,
                                modifier = Modifier,
                                navController = navController,
                                userViewModel = signUpViewModel,
                                storyViewModel = storyViewModel,
                                taskListViewModel = taskListViewModel
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

                        composable("ProfileScreen"){
                            ProfileScreen(
                                modifier = Modifier,
                                context = this@MainActivity,
                                postViewModel = postViewModel,
                                navController = navController,
                                userViewModel = signUpViewModel
                            )
                        }

                        composable(
                            "ShareStoryScreen/{imageUri}/{taskTitle}/{taskDesc}/{taskId}"
                        ) { backStackEntry ->
                            val imageUri = backStackEntry.arguments?.getString("imageUri") ?: ""
                            val taskTitle = backStackEntry.arguments?.getString("taskTitle") ?: ""
                            val taskDesc = backStackEntry.arguments?.getString("taskDesc") ?: ""
                            val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull() ?: -1

                            ShareStoryScreen(
                                navController = navController,
                                storyViewModel = storyViewModel,
                                imageUriStr = imageUri,
                                taskTitle = taskTitle,
                                taskDesc = taskDesc,
                                taskId = taskId
                            )
                        }

                        composable("StoryFullScreen") {
                            StoryFullScreen(storyViewModel, context = this@MainActivity, navController)
                        }

                        composable("ProfileScreenPostPreview") {
                            ProfileScreenPostPreview(
                                modifier = Modifier,
                                postviewmodel = postViewModel,
                                context = this@MainActivity,
                                navController = navController
                            )
                        }


                    }
                }
            }
        }
    }
}
