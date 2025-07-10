package com.example.momentum_app.view.storyscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.momentum_app.R
import com.example.momentum_app.model.Story
import com.example.momentum_app.viewmodel.StoryViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun StoryScreenDisplay(
    storyViewModel: StoryViewModel,
    navController: NavController
) {
    val storyList by storyViewModel.stories.collectAsState()

    LaunchedEffect(Unit) {
        storyViewModel.fetchStories()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        Text(
            text = "Community Stories",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        if (storyList.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No stories yet!", color = Color.Gray)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp)
            ) {
                items(storyList) { story ->
                    StoryCard(story = story, onClick = {
                        val encodedImage = URLEncoder.encode(story.image, StandardCharsets.UTF_8.toString())
                        val encodedCaption = URLEncoder.encode(story.caption, StandardCharsets.UTF_8.toString())
                        val encodedUsername = URLEncoder.encode(story.username, StandardCharsets.UTF_8.toString())

                        navController.navigate("story_display/$encodedImage/$encodedCaption/$encodedUsername")
                    })
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun StoryCard(story: Story, onClick: () -> Unit) {
    val formattedDate = remember(story.createdAt) {
        story.createdAt?.let {
            val date = Date(it)
            val format = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
            format.format(date)
        } ?: ""
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.background(Color.White)
        ) {
            AsyncImage(
                model = "data:image/jpeg;base64,${story.image}",
                contentDescription = "Story Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.dummy_pfp1),
                        contentDescription = "Profile",
                        modifier = Modifier
                            .size(36.dp)
                            .padding(end = 8.dp)
                    )
                    Text(
                        text = story.username,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                }

                if (story.taskId != -1) {
                    Text(
                        text = "Task ID: ${story.taskId}",
                        fontSize = 14.sp,
                        color = Color.DarkGray
                    )
                }

                Text(
                    text = story.caption,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(top = 4.dp)
                )

                if (formattedDate.isNotEmpty()) {
                    Text(
                        text = formattedDate,
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}
