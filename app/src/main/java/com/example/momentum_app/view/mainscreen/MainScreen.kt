package com.example.momentum_app.view.mainscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.momentum_app.MainActivity
import com.example.momentum_app.R
import com.example.momentum_app.model.Post
import com.example.momentum_app.model.PostsData
import com.example.momentum_app.model.Task
import com.example.momentum_app.view.home.Home
import com.example.momentum_app.view.profilescreen.ProfileScreen
import com.example.momentum_app.view.progressscreen.ProgressScreen
import com.example.momentum_app.view.rewardsscreen.RewardsScreen
import com.example.momentum_app.view.tasklistscreen.TaskListScreen
import com.example.momentum_app.viewmodel.PostViewModel
import com.example.momentum_app.viewmodel.SignUpViewModel
import com.example.momentum_app.viewmodel.StoryViewModel
import com.example.momentum_app.viewmodel.TaskListViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    context: MainActivity,
    postViewModel: PostViewModel,
    userViewModel: SignUpViewModel,
    storyViewModel: StoryViewModel,
    taskListViewModel: TaskListViewModel
)
 {
    val navItemList = listOf(
        NavItem("Home", R.drawable.home_icon, R.drawable.home_onclicked_icon),
        NavItem("Rewards", R.drawable.rewards_icon, R.drawable.rewards_onclicked_icon),
        NavItem("Task List", R.drawable.tasklist_icon, R.drawable.tasklist_onclicked_icon),
        NavItem("Progress", R.drawable.progress_icon, R.drawable.progress_onclicked_icon),
        NavItem("Profile", R.drawable.profile_icon, R.drawable.profile_onclicked_icon)
    )

    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar{
                navItemList.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index },
                        icon = {
                            Image(
                                painter = painterResource(
                                    id = if (selectedIndex == index) item.iconSelected else item.iconUnselected
                                ),
                                contentDescription = item.label,
                                modifier = modifier.size(30.dp)
                            )
                        },
                        label = {
                            Text(text = item.label)
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        when (selectedIndex) {
            0 -> Home(
                modifier = modifier.padding(innerPadding),
                navController = navController,
                context = context,
                storyViewModel = storyViewModel
            )
            1 -> RewardsScreen(modifier = modifier.padding(innerPadding))
            2 -> TaskListScreen(
                viewModel = taskListViewModel,
                context = context,
                modifier = modifier.padding(innerPadding),
                navController = navController
            )
            3 -> ProgressScreen(modifier = modifier.padding(innerPadding))
            4 -> ProfileScreen(
                modifier = modifier.padding(innerPadding),
                context = context,
                postViewModel = postViewModel,
                navController = navController,
                userViewModel = userViewModel
            )
        }
    }
}

data class NavItem(
    val label: String,
    val iconUnselected: Int,
    val iconSelected: Int

)
