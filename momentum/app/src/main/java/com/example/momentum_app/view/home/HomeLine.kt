package com.example.momentum_app.view.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HomeLine(modifier: Modifier = Modifier){

    Spacer(modifier = modifier
        .fillMaxWidth()
        .background(Color.LightGray)
        .height(0.5.dp)
    )

}