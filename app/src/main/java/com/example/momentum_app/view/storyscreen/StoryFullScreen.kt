package com.example.momentum_app.view.storyscreen

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Base64
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.momentum_app.R
import com.example.momentum_app.viewmodel.StoryViewModel

@Composable
fun StoryFullScreen(
    storyViewModel: StoryViewModel,
    context: Context,
    navController: NavController
) {
    val story = storyViewModel.selectedStory.collectAsState().value
    var showDeleteDialog by remember { mutableStateOf(false) }

    if (story == null) {
        Text("No story selected", color = Color.White)
        return
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        StoryImage(story.image)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color.Gray, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(story.username, color = Color.White, fontSize = 24.sp)
                }

                Image(
                    painter = painterResource(id = R.drawable.delete_icon2),
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            showDeleteDialog = true
                        },
                    contentDescription = "Delete Button"
                )

            }

            Text(
                text = story.caption,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 32.dp)
            )
        }

        if (showDeleteDialog) {
            StoryDeleteDialog(
                onDismiss = { showDeleteDialog = false },
                onDeleteStory = {
                    val storyId = story.id
                    if (storyId != null) {
                        storyViewModel.deleteStory(
                            storyId,
                            onResult = { success, _ ->
                                if (success) {
                                    Toast.makeText(context, "Story Deleted", Toast.LENGTH_SHORT).show()
                                }
                            }
                        )
                    }
                    navController.navigate("MainScreen")
                }

            )
        }
    }
}

@Composable
fun StoryImage(base64Image: String) {
    val imageBitmap = remember(base64Image) {
        try {
            val pureBase64 = base64Image.substringAfter(",")
            val decodedBytes = Base64.decode(pureBase64, Base64.DEFAULT)
            BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)?.asImageBitmap()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    imageBitmap?.let {
        Image(
            bitmap = it,
            contentDescription = "Story image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    } ?: Text("Image could not be loaded", color = Color.White)
}

@Composable
fun StoryDeleteDialog(
    onDismiss: () -> Unit,
    onDeleteStory: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        text = { Text("Would you like to delete this story?") },
        confirmButton = {
            TextButton(onClick = onDeleteStory) {
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
