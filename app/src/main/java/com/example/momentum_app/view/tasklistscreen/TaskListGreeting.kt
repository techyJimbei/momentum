package com.example.momentum_app.view.tasklistscreen

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TaskListGreeting(
    modifier: Modifier = Modifier,
    context: Context
) {
    Column(
        modifier = modifier.padding(start = 16.dp, top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hello, ${getUsername(context)}",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 40.sp,
            color = Color(0xFF1A78D6)
        )
    }
}

fun getUsername(context: Context): String {
    val sharedPref = context.getSharedPreferences("UserData", Context.MODE_PRIVATE)
    return sharedPref.getString("username", "User") ?: "User"
}
