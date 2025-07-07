package com.example.momentum_app.view.profilescreen

import android.content.Context
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.momentum_app.MainActivity
import com.example.momentum_app.model.Post
import com.example.momentum_app.model.PostsData
import com.example.momentum_app.model.Task
import com.example.momentum_app.viewmodel.PostViewModel
import com.example.momentum_app.viewmodel.SignUpViewModel

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    context: Context,
    postViewModel: PostViewModel,
    navController: NavController,
    userViewModel: SignUpViewModel
)
{
    ProfileScreenHeader(modifier = modifier)
    ProfileScreenUserInfo(
        modifier = modifier,
        context = context,
        postViewModel = postViewModel,
        navController = navController,
        userViewModel = userViewModel
    )

}