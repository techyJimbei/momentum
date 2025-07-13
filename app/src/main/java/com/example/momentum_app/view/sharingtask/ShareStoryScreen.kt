package com.example.momentum_app.view.sharingtask

import android.content.Context
import android.net.Uri
import android.util.Base64
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.momentum_app.R
import com.example.momentum_app.view.tasklistscreen.getUsername
import com.example.momentum_app.viewmodel.StoryViewModel
import java.io.InputStream

@Composable
fun ShareStoryScreen(
    navController: NavHostController,
    storyViewModel: StoryViewModel,
    imageUriStr: String,
    taskTitle: String,
    taskDesc: String,
    taskId: Int
) {
    val context = LocalContext.current
    val imageUri = imageUriStr.toUri()
    val username = getUsername(context)
    val pfpPlaceholder = painterResource(id = R.drawable.dummy_pfp1)

    var caption by remember { mutableStateOf("") }

    val imageBase64 = remember(imageUri) {
        encodeImageToBase64(context, imageUri)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AsyncImage(
            model = imageUri,
            contentDescription = "Selected Story Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)),
                        startY = 200f
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = pfpPlaceholder,
                    contentDescription = "PFP",
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color.Gray, shape = RoundedCornerShape(24.dp))
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = username,
                    color = Color.White,
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = taskTitle,
                color = Color.White,
                fontSize = 22.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = taskDesc,
                color = Color.White,
                fontSize = 16.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.weight(1f))

            OutlinedTextField(
                value = caption,
                onValueChange = { caption = it },
                placeholder = { Text("Add a caption...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White.copy(alpha = 0.2f), RoundedCornerShape(8.dp)),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.LightGray,
                    focusedPlaceholderColor = Color.LightGray,
                    unfocusedPlaceholderColor = Color.LightGray
                )
            )


            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        storyViewModel.createStory(
                            context = context,
                            imageBase64 = imageBase64 ?: "",
                            caption = caption,
                            taskId = taskId,
                            onResult = { success, message ->
                                if (success) {
                                    navController.navigate("TaskListScreen")
                                }
                            }
                        )
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF89C4FF))
                ) {
                    Text("Confirm Story")
                }

                Button(
                    onClick = {
                        navController.popBackStack()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF867D))
                ) {
                    Text("Cancel")
                }
            }
        }
    }
}

fun encodeImageToBase64(context: Context, uri: Uri): String? {
    return try {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        val bytes = inputStream?.readBytes()
        inputStream?.close()
        Base64.encodeToString(bytes, Base64.DEFAULT)
    } catch (e: Exception) {
        null
    }
}

