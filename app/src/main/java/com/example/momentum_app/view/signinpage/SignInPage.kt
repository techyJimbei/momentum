package com.example.momentum_app.view.signinpage

import SignUpViewModel
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.momentum_app.MainActivity
import com.example.momentum_app.R

@Composable
fun SignInPage(navController: NavHostController, viewModel: SignUpViewModel, context: MainActivity) {

    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Welcome Back", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(Modifier.size(16.dp))

        OutlinedTextField(value = username, onValueChange = {username = it}, label = {
            Text(text = "Enter Username")
        }, shape = RoundedCornerShape(16.dp))

        Spacer(Modifier.size(16.dp))

        OutlinedTextField(value = password, onValueChange = {password = it}, label = {
            Text(text = "Enter Password")
        }, shape = RoundedCornerShape(16.dp))

        Spacer(Modifier.size(24.dp))

        Button(
            onClick ={
                if (username.isBlank() || password.isBlank()) {
                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.loginUser(username, password) { success, message ->
                        if (success) {
                            navController.navigate("MainScreen")
                        } else {
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            },
            modifier = Modifier
                .size(200.dp, 48.dp),
            shape = RoundedCornerShape(80.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1A78D6)
            )
        ) {
            androidx.compose.material3.Text(
                text = "LOGIN",
                color = Color.White,
            )
        }

        Spacer(Modifier.size(24.dp))

        Text(text = "Forgot Password?",
            fontSize = 14.sp,
            color =  Color.DarkGray,
            modifier =  Modifier.clickable {

            })

        Spacer(Modifier.size(24.dp))

        Text(text = "OR", fontSize = 16.sp, color = Color.Gray)

        Spacer(Modifier.size(16.dp))

        Text(text = "Login with", fontSize = 14.sp, color = Color.Gray)

        Spacer(Modifier.size(32.dp))

        Row(
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ){
            Image(painter = painterResource(id =  R.drawable.facebook_icon_512),
                contentDescription = null, Modifier.size(56.dp).clickable {

                })

            Spacer(Modifier.size(24.dp))

            Image(painter = painterResource(id = R.drawable.google_icon),
                contentDescription = null, Modifier.size(50.dp).clickable {

                })

            Spacer(Modifier.size(24.dp))

            Image(painter = painterResource(id =  R.drawable.x_icon),
                contentDescription = null, Modifier.size(56.dp).clickable {

                })

        }

    }

}