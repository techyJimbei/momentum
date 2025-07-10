package com.example.momentum_app.view.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.momentum_app.MainActivity
import com.example.momentum_app.model.Task
import com.example.momentum_app.viewmodel.StoryViewModel

@Composable
fun Home(modifier: Modifier = Modifier, navController: NavHostController, context: MainActivity, storyViewModel: StoryViewModel){
    HomeRow(
        modifier = modifier,
        header = { HomeHeader() },
        stories = { StoryRow(storyViewModel = storyViewModel, navController = navController) },
        line = { HomeLine()},
        posts = { postItemList(modifier)}
    )

}