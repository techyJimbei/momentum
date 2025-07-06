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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.momentum_app.R
import com.example.momentum_app.model.PostsData
import com.example.momentum_app.model.PostsList
import com.example.momentum_app.ui.theme.Dark
import com.example.momentum_app.ui.theme.Logo
import com.example.momentum_app.ui.theme.Powder_Blue


@Composable
fun PostsRow(
    modifier: Modifier = Modifier,
    data: PostsData,
) {
    val pager = rememberPagerState(initialPage = 0) { 3 }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .fillMaxWidth()
    ) {
        // Username and PFP
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(data.pfp),
                contentDescription = null,
                modifier = Modifier.size(38.dp)
            )

            Text(
                text = data.username,
                fontWeight = FontWeight.W600,
                fontSize = 18.sp
            )
        }

        // Post Image Pager
        PostPicture(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 0.dp),
            pagerState = pager,
            data = data
        )

        // Actions Row
        PostAction(
            modifier = Modifier.padding(top = 4.dp),
            pagerState = pager
        )

        // Caption Row
        PostCaption(
            modifier = Modifier.padding(horizontal = 12.dp),
            data = data
        )

        // Bottom Divider
        Spacer(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
                .height(0.5.dp)
                .background(Color.LightGray)
        )
    }
}

@Composable
fun PostPicture(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    data: PostsData
) {
    HorizontalPager(
        modifier = modifier,
        state = pagerState
    ) {
        Image(
            painter = painterResource(data.post),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun PostAction(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.like_icon),
                contentDescription = null,
                modifier = Modifier.size(28.dp).clickable { }
            )
            Image(
                painter = painterResource(R.drawable.comments_icon),
                contentDescription = null,
                modifier = Modifier.size(28.dp).clickable { }
            )
            Image(
                painter = painterResource(R.drawable.share_icon),
                contentDescription = null,
                modifier = Modifier.size(28.dp).clickable { }
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(pagerState.pageCount) {
                Spacer(
                    modifier = Modifier
                        .size(8.dp)
                        .background(
                            color = if (pagerState.currentPage == it) Logo else Powder_Blue,
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
) {
    Row(
        modifier = modifier.padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = data.username,
            fontWeight = FontWeight.W500,
            fontSize = 15.sp,
            color = Dark
        )

        Text(
            text = data.caption,
            fontWeight = FontWeight.W400,
            fontSize = 14.sp,
            color = Color.Black
        )
    }
}

fun LazyListScope.postItemList(modifier: Modifier) {
    items(PostsList, key = { it.id }) {
        PostsRow(
            data = it,
            modifier = modifier
        )
    }
}
