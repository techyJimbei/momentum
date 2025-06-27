package com.example.momentum_app.view.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.momentum_app.R
import com.example.momentum_app.model.PostsData
import com.example.momentum_app.model.PostsList
import com.example.momentum_app.model.Task
import com.example.momentum_app.ui.theme.Dark
import com.example.momentum_app.ui.theme.Logo
import com.example.momentum_app.ui.theme.Powder_Blue



@Composable
fun PostsRow(
    modifier: Modifier = Modifier,
    data: PostsData,
//    task: Task
){
    val pager = rememberPagerState(initialPage = 0){3}
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(data.pfp),
                contentDescription = null,
                modifier =  modifier
                    .size(38.dp)
            )

            Text(
                text = data.username,
                fontWeight = FontWeight.W600,
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

//        Text(
//            text = "${task.title}: ${task.description}",
//            fontSize = 16.sp,
//            fontWeight = FontWeight.W700,
//            fontStyle = FontStyle.Italic,
//            color = Color.DarkGray
//            )

        Spacer(modifier = Modifier.height(16.dp))

        PostPicture(modifier = Modifier, pagerState = pager, data = data)
        PostAction(pagerState = pager)
        PostCaption(data = data)

        Spacer(modifier = modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .height(0.3.dp)
        )

    }
}


@Composable
fun PostPicture(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    data: PostsData
){
    HorizontalPager(
        modifier = modifier, state = pagerState
    ) {
        Image(
            painter = painterResource(data.post),
            contentDescription = null,
            modifier.fillMaxWidth()
        )
    }

}

@Composable
fun PostAction(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.like_icon),
                contentDescription = null,
                modifier = modifier
                    .size(30.dp)
                    .clickable {  })
            Image(
                painter = painterResource(R.drawable.comments_icon),
                contentDescription = null,
                modifier = modifier
                    .size(30.dp)
                    .clickable {  })
            Image(
                painter = painterResource(R.drawable.share_icon),
                contentDescription = null,
                modifier = modifier
                    .size(30.dp)
                    .clickable {  })
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(pagerState.pageCount){
                Spacer(
                    modifier = modifier
                        .size(8.dp)
                        .background(
                            color = if(pagerState.currentPage == it) Logo else Powder_Blue,
                            shape = CircleShape
                        )
                )
            }
        }

    }
}

@Composable
fun PostCaption(
    modifier: Modifier = Modifier,
    data: PostsData
){
    Row(
        modifier = modifier.padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text =  data.username,
            fontWeight = FontWeight.W400,
            fontSize = 16.sp,
            color = Dark
            )

        Text(
            text =  data.caption,
            fontWeight = FontWeight.W400,
            fontSize = 14.sp,
            color = Color.Black
        )
    }

}

fun LazyListScope.postItemList(){
    items(PostsList, key = {it.id}){
        PostsRow(data =  it)
    }
}

