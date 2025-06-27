package com.example.momentum_app.view.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.momentum_app.MainActivity
import com.example.momentum_app.model.Task

@Composable
fun Home(modifier: Modifier = Modifier, navController: NavHostController, context: MainActivity){
    HomeRow(
        modifier = modifier,
        header = { HomeHeader() },
        stories = { storyItemList() },
        line = { HomeLine()},
        posts = { postItemList()}
    )

}