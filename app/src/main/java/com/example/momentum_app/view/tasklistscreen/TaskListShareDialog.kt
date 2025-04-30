package com.example.momentum_app.view.tasklistscreen

import androidx.compose.material3.*
import androidx.compose.runtime.*

@Composable
fun TaskListShareDialog(
    taskTitle: String,
    onDismiss: () -> Unit,
    onSharePost: () -> Unit,
    onShareStory: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("🎉 Task Completed!") },
        text = { Text("Would you like to share \"$taskTitle\" as a Story or Post?") },
        confirmButton = {
            TextButton(onClick = onSharePost) {
                Text("Post")
            }
        },
        dismissButton = {
            TextButton(onClick = onShareStory) {
                Text("Story")
            }
        }
    )
}
