package com.example.momentum_app.view.onboardinggetstarted

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.momentum_app.MainActivity
import com.example.momentum_app.R

@Composable
fun OnboardingGetStarted(navController: NavHostController, context: MainActivity){

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.momentum_onboarding_logo),
                contentDescription = null,
                modifier = Modifier
                    .size(640.dp)
                    .align(Alignment.CenterHorizontally) // Corrected here
            )

            Button(
                onClick ={
                    navController.navigate("OnboardingScreen")
                },
                modifier = Modifier
                    .size(320.dp, 48.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1A78D6)
                )
            ) {
                Text(
                    text = "GET STARTED",
                    color = Color.White,
                )
            }

            Spacer(modifier = Modifier.size(16.dp))

            OutlinedButton(
                onClick = {
                    navController.navigate("SignInPage")
                },
                modifier = Modifier
                    .size(320.dp, 48.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFFFFF)
                ),
                border = BorderStroke(1.dp, Color(0xFF1A78D6))
            ) {
                Text(
                    text = "I ALREADY HAVE AN ACCOUNT",
                        color =  Color(0xFF1A78D6)
                )
            }

        }
    }


}
