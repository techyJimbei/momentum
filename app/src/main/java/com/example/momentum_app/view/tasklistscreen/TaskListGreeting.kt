package com.example.momentum_app.view.tasklistscreen

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun TaskListGreeting(
    modifier: Modifier = Modifier,
    context: Context){

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "HELLO, ${getUsername(context)}",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 30.sp)

    }

}

fun getUsername(context: Context): String {
    val sharedPref = context.getSharedPreferences("UserData", Context.MODE_PRIVATE)
    return sharedPref.getString("username", "User") ?: "User"
}
