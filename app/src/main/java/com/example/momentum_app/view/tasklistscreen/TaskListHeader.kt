package com.example.momentum_app.view.tasklistscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.momentum_app.R

@Composable
fun TaskListHeader(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(color = Color.White)
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.dummy_pfp1),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
        )

        Image(
            painter = painterResource(id = R.drawable.three_dots_icon),
            contentDescription = null,
            modifier = Modifier
                .size(28.dp)
                .clickable { }
        )
    }
}
