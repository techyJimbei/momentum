package com.example.momentum_app.view.home

 import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.momentum_app.R

@Composable
fun HomeHeader(
    modifier: Modifier = Modifier
){

    val customFont = FontFamily(
        Font(R.font.jomhuria_regular)
    )

    Row(
        modifier = modifier
            .background(color = Color.White)
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(painter = painterResource(id = R.drawable.camera_icon),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .clickable {

                })

        Text(text = "MOMENTUM",
            color = Color(0xFF1A78D6),
            fontFamily = customFont,
            fontSize = 70.sp,
            modifier = modifier.padding(top =  10.dp)
            )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.activity_icon),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .clickable {

                    })

            Image(painter = painterResource(id = R.drawable.chats_icon),
                contentDescription = null,
                modifier = Modifier
                    .size(28.dp)
                    .clickable {

                    })
        }
    }

}