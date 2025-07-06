package com.example.momentum_app.view.profilescreen

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.momentum_app.R
import com.example.momentum_app.ui.theme.Logo
import com.example.momentum_app.viewmodel.PostViewModel

@Composable
fun ProfileScreenUserInfo(
    modifier: Modifier = Modifier,
    context: Context,
    postViewModel: PostViewModel,
    navController: NavController
) {
    val posts by postViewModel.posts.collectAsState()

    LaunchedEffect(Unit) {
        postViewModel.fetchPosts()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.dummy_pfp1),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
        )

        Text(
            text = "@${getUsername(context)}",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Logo
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "+10000 $ coins",
            fontSize = 20.sp,
            fontWeight = FontWeight.W300,
            fontStyle = FontStyle.Italic,
            color = Color(0xFFFEBA17)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfileStatItem(title = "  Goals  ", value = "0")
            ProfileStatItem(title = "Followers", value = "0")
            ProfileStatItem(title = "Following", value = "0")
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .background(Color.LightGray)
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(posts) { post ->
                Box(
                    modifier = modifier.clickable {
                        postViewModel.selectPost(post)
                        navController.navigate(
                            "ProfileScreenPostPreview/" +
                                    "${Uri.encode(post.username)}/" +
                                    "${Uri.encode(post.caption)}/" +
                                    "${Uri.encode(post.image)}/" +
                                    "${Uri.encode(post.taskTitle)}/" +
                                    "${Uri.encode(post.taskDescription)}"
                        )
                    }
                ) {
                    PostGridItem(post.image)
                }
            }
        }
    }
}


@Composable
fun PostGridItem(base64Image: String) {
    val imageBitmap = remember(base64Image) {
        decodeBase64ToBitmap(base64Image)?.asImageBitmap()
    }

    imageBitmap?.let {
        Image(
            bitmap = it,
            contentDescription = "Post image",
            modifier = Modifier
                .aspectRatio(1f)

        )
    }
}

fun decodeBase64ToBitmap(base64Str: String): Bitmap? {
    return try {
        val decodedBytes = Base64.decode(base64Str, Base64.DEFAULT)
        BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    } catch (e: Exception) {
        null
    }
}

@Composable
fun ProfileStatItem(title: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            text = value,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color.DarkGray
        )
    }
}

fun getUsername(context: Context): String {
    val sharedPref = context.getSharedPreferences("UserData", Context.MODE_PRIVATE)
    return sharedPref.getString("username", "User") ?: "User"
}
