package com.example.momentum_app.view.profilescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.momentum_app.R

@Composable
fun ProfileScreenHeader(
    modifier: Modifier
){

    Row(
        modifier = modifier
            .background(color = Color.Transparent)
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.End
    ) {

        Image(painter = painterResource(id = R.drawable.setting),
            contentDescription = null,
            modifier = Modifier
                .size(32.dp)
                .clickable {
                })
    }
}

@Composable
fun SettingDialog(

){

}
