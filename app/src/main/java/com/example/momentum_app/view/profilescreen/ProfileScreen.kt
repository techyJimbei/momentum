package com.example.momentum_app.view.profilescreen

import android.content.Context
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.momentum_app.MainActivity
import com.example.momentum_app.viewmodel.PostViewModel

@Composable
fun ProfileScreen(modifier: Modifier = Modifier, context: Context, postViewModel: PostViewModel){

    ProfileScreenHeader(modifier = modifier)
    ProfileScreenUserInfo(modifier = modifier, context = context, postViewModel = postViewModel)

}