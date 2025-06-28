package com.example.momentum_app.view.profilescreen

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
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.momentum_app.model.Post
import com.example.momentum_app.ui.theme.Dark
import com.example.momentum_app.ui.theme.Logo
import com.example.momentum_app.ui.theme.Powder_Blue
import com.example.momentum_app.viewmodel.PostViewModel


@Composable
fun ProfileScreenPostPreview(
    modifier: Modifier = Modifier,
    postViewModel: PostViewModel
) {
    val post = postViewModel.selectedPost.value

    if (post != null) {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            item {
                PostsRow(post = post)
            }
        }
    } else {
        Text("No post selected.")
    }
}

@Composable
fun PostsRow(
    modifier: Modifier = Modifier,
    post: Post
) {
    val pager = rememberPagerState(initialPage = 0) { 1 }

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
                painter = painterResource(R.drawable.dummy_pfp1),
                contentDescription = null,
                modifier = modifier.size(38.dp)
            )

            Text(
                text = post.username,
                fontWeight = FontWeight.W600,
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "${post.taskTitle}: ${post.taskDescription}",
            fontSize = 16.sp,
            fontWeight = FontWeight.W700,
            fontStyle = FontStyle.Italic,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(16.dp))

        PostPicture(pagerState = pager, post = post)
        PostAction(pagerState = pager)
        PostCaption(post = post)

        Spacer(
            modifier = modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .height(0.3.dp)
        )
    }
}

@Composable
fun PostPicture(
    pagerState: PagerState,
    post: Post
) {
    HorizontalPager(state = pagerState) {
        PostGridItem(post.image)
    }
}

@Composable
fun PostAction(
    pagerState: PagerState
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(R.drawable.like_icon), contentDescription = null, modifier = Modifier.size(30.dp).clickable {})
            Image(painter = painterResource(R.drawable.comments_icon), contentDescription = null, modifier = Modifier.size(30.dp).clickable {})
            Image(painter = painterResource(R.drawable.share_icon), contentDescription = null, modifier = Modifier.size(30.dp).clickable {})
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp),
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
fun PostCaption(post: Post) {
    Row(
        modifier = Modifier.padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = post.username,
            fontWeight = FontWeight.W400,
            fontSize = 16.sp,
            color = Dark
        )

        Text(
            text = post.caption,
            fontWeight = FontWeight.W400,
            fontSize = 14.sp,
            color = Color.Black
        )
    }
}





