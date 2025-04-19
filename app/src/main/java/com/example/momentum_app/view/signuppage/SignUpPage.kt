package com.example.momentum_app.view.signuppage

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
fun SignUpPage(navController: NavHostController, context: MainActivity) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Create your Account", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(Modifier.size(16.dp))

        OutlinedTextField(value = "", onValueChange = {}, label = {
            Text(text = "Create Username")
        }, shape = RoundedCornerShape(16.dp))

        Spacer(Modifier.size(16.dp))

        OutlinedTextField(value = "", onValueChange = {}, label = {
            Text(text = "Enter Email Address")
        }, shape = RoundedCornerShape(16.dp))

        Spacer(Modifier.size(16.dp))

        OutlinedTextField(value = "", onValueChange = {}, label = {
            Text(text = "Enter Password")
        }, shape = RoundedCornerShape(16.dp))

        Spacer(Modifier.size(16.dp))

        OutlinedTextField(value = "", onValueChange = {}, label = {
            Text(text = "Re-enter Password")
        }, shape = RoundedCornerShape(16.dp))

        Spacer(Modifier.size(24.dp))

        Button(
            onClick ={
                navController.navigate("MainScreen")
            },
            modifier = Modifier
                .size(200.dp, 48.dp),
            shape = RoundedCornerShape(80.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1A78D6)
            )
        ) {
            androidx.compose.material3.Text(
                text = "CREATE",
                color = Color.White,
            )
        }

        Spacer(Modifier.size(24.dp))

        Text(text = "OR", fontSize = 16.sp, color = Color.Gray)

        Spacer(Modifier.size(24.dp))

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

