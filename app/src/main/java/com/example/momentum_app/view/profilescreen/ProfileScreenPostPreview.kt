package com.example.momentum_app.view.profilescreen

import android.content.Context
import android.widget.Toast
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
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.momentum_app.R
import com.example.momentum_app.model.Post
import com.example.momentum_app.ui.theme.Dark
import com.example.momentum_app.ui.theme.Logo
import com.example.momentum_app.ui.theme.Powder_Blue
import com.example.momentum_app.viewmodel.PostViewModel


@Composable
fun ProfileScreenPostPreview(
    modifier: Modifier = Modifier,
    post: Post?,
    postviewmodel: PostViewModel,
    context: Context,
    navController: NavController
) {
    if (post != null) {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 40.dp)
        ) {
            item {
                PostsRow(
                    post = post,
                    modifier = modifier,
                    postviewmodel = postviewmodel,
                    context = context,
                    navController = navController
                )
            }
        }
    } else {
        Text("No post selected.")
    }
}


@Composable
fun PostsRow(
    modifier: Modifier = Modifier,
    post: Post,
    postviewmodel: PostViewModel,
    context: Context,
    navController: NavController
) {
    val pager = rememberPagerState(initialPage = 0) { 1 }
    var showpostDeleteDialog by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        // Username + PFP
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ){
                Image(
                    painter = painterResource(R.drawable.dummy_pfp1),
                    contentDescription = null,
                    modifier = modifier.size(40.dp)
                )

                Text(
                    text = post.username,
                    fontWeight = FontWeight.W600,
                    fontSize = 18.sp
                )
            }

            Image(
                painter =  painterResource(id = R.drawable.delete_icon2),
                contentDescription = "Delete button",
                modifier =  modifier
                    .size(30.dp)
                    .clickable {
                        showpostDeleteDialog = true
                    }
            )
        }

        // Task Title + Description
        Text(
            text = "${post.taskTitle}: ${post.taskDescription}",
            fontSize = 15.sp,
            fontWeight = FontWeight.W600,
            fontStyle = FontStyle.Italic,
            color = Color.DarkGray,
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        // Post Image
        PostPicture(pagerState = pager, post = post)

        // Action buttons
        PostAction(pagerState = pager)

        // Caption
        PostCaption(post = post)

        // Divider
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .background(Color.LightGray)
                .padding(top = 4.dp)
        )
    }

    if(showpostDeleteDialog){
        PostDeleteDialog(
            onDismiss = {showpostDeleteDialog = false},
            onDeletePost = {
                val postId = post.id
                if (postId != null) {
                    postviewmodel.deletePost(postId, onResult = {success, _ ->
                        if (success) {
                            Toast.makeText(context, "Post Deleted", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        }
                        else {
                            Toast.makeText(context, "Deletion failed", Toast.LENGTH_SHORT).show()
                        }
                    })
                }

            }
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

@Composable
fun PostDeleteDialog(
    onDismiss: () -> Unit,
    onDeletePost: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        text = {Text("Would you like to delete this post?") },
        confirmButton = {
            TextButton(onClick = onDeletePost) {
                Text("Yes")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}






