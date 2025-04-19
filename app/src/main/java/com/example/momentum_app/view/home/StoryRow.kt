package com.example.momentum_app.view.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.momentum_app.model.StoryData
import com.example.momentum_app.model.StoryList
import com.example.momentum_app.ui.theme.Logo

@Composable
fun StoryRow(
    modifier: Modifier = Modifier,
    data: StoryData
){
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(data.pfp),
            contentDescription = "User pfp",
            modifier = modifier
                .size(70.dp)
                .drawBehind {
                    drawCircle(
                        color = Logo,
                        style = Stroke(
                            width = 5.dp.toPx()
                        )
                    )
                }
                .clickable {
                    //TODO
                })

        Text(
            text =  data.username,
            fontSize = 15.sp,
            fontWeight = FontWeight.W400,
            color = Color.Black
            )
    }

}


fun LazyListScope.storyItemList(){
    items(StoryList, key = { it.id }) {
        StoryRow(data = it)
    }
}