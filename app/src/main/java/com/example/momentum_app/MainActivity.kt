package com.example.momentum_app

import AppTheme
import android.net.Uri
import com.example.momentum_app.viewmodel.SignUpViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.momentum_app.model.Post
import com.example.momentum_app.model.Story
import com.example.momentum_app.view.mainscreen.MainScreen
import com.example.momentum_app.view.onboardinggetstarted.OnboardingGetStarted
import com.example.momentum_app.view.onboardingscreen.OnboardingScreen
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
import java.net.URLDecoder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                            SplashScreen(navController = navController, context = this@MainActivity)
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
                            StoryFullScreen(storyViewModel)
                        }

                        composable(
                            route = "ProfileScreenPostPreview/{username}/{caption}/{image}/{title}/{description}",
                            arguments = listOf(
                                navArgument("username") { type = NavType.StringType },
                                navArgument("caption") { type = NavType.StringType },
                                navArgument("image") { type = NavType.StringType },
                                navArgument("title") { type = NavType.StringType },
                                navArgument("description") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val username = backStackEntry.arguments?.getString("username") ?: ""
                            val caption = backStackEntry.arguments?.getString("caption") ?: ""
                            val image = backStackEntry.arguments?.getString("image") ?: ""
                            val title = backStackEntry.arguments?.getString("title") ?: ""
                            val description = backStackEntry.arguments?.getString("description") ?: ""

                            val currentTime = System.currentTimeMillis()

                            val post = Post(
                                image = image,
                                caption = caption,
                                taskTitle = title,
                                taskDescription = description,
                                timestamp = currentTime,
                                username = username
                            )


                            postViewModel.selectPost(post)

                            ProfileScreenPostPreview(
                                modifier = Modifier,
                                post = post
                            )
                        }
                    }
                }
            }
        }
    }
}
