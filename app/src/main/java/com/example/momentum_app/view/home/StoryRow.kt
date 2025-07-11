package com.example.momentum_app.view.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.momentum_app.R
import com.example.momentum_app.model.Story
import com.example.momentum_app.model.StoryData
import com.example.momentum_app.viewmodel.StoryViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun StoryRow(
    storyViewModel: StoryViewModel,
    navController: NavController
) {
    val storyList by storyViewModel.stories.collectAsState()

    LaunchedEffect(Unit) {
        storyViewModel.fetchStories()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        Text(
            text = "Stories",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(storyList) { story ->
                StoryBubble(story = story, onClick = {
                    val encodedImage = URLEncoder.encode(story.image, StandardCharsets.UTF_8.toString())
                    val encodedCaption = URLEncoder.encode(story.caption, StandardCharsets.UTF_8.toString())
                    val encodedUsername = URLEncoder.encode(story.username, StandardCharsets.UTF_8.toString())

                    navController.navigate("StoryFullScreen/$encodedImage/$encodedCaption/$encodedUsername")
                })
            }
        }
    }
}

@Composable
fun StoryBubble(
    story: Story,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(80.dp)
            .clickable { onClick() }
    ) {
        Card(
            shape = CircleShape,
            modifier = Modifier.size(64.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            AsyncImage(
                model = painterResource(id = R.drawable.dummy_pfp1),
                contentDescription = "Story Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = story.username,
            fontSize = 12.sp,
            maxLines = 1
        )
    }
}
