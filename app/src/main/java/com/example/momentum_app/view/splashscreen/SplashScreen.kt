package com.example.momentum_app.view.splashscreen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.momentum_app.MainActivity
import com.example.momentum_app.R
import com.example.momentum_app.viewmodel.SignUpViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
    context: Context,
    viewModel: SignUpViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF89C4FF))
    ) {
        Image(
            painter = painterResource(id = R.drawable.momentum_logo_resized),
            contentDescription = null,
            modifier = Modifier
                .size(640.dp)
                .align(alignment = Alignment.Center)
        )
    }

    LaunchedEffect(Unit) {
        delay(2000)

        viewModel.verifyTokenOnAppStart(context) { isValid, username ->
            navController.popBackStack()
            if (isValid) {
                navController.navigate("MainScreen")
            } else {
                navController.navigate("OnboardingGetStarted")
            }
        }
    }
}

