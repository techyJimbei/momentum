package com.example.momentum_app.view.tasklistscreen

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.momentum_app.model.Task
import com.example.momentum_app.viewmodel.TaskListViewModel

@Composable
fun TaskListScreen(
    viewModel: TaskListViewModel,
    modifier: Modifier,
    context: Context,
    navController: NavHostController
) {
    var showAddDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }
    var taskToEdit by remember { mutableStateOf<Task?>(null) }
    var showShareDialog by remember { mutableStateOf(false) }
    var completedTaskTitle by remember { mutableStateOf("") }
    var completedTaskDesc by remember { mutableStateOf("") }
    var completedTaskId by remember { mutableStateOf<Int?>(null) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val taskList by viewModel.taskList.collectAsState()
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            imageUri = it
            navController.navigate(
                "ShareStoryScreen/${Uri.encode(it.toString())}/${completedTaskTitle}/${completedTaskDesc}/${completedTaskId}"
            )
        }
    }



    Scaffold(
        containerColor = Color.White,
        floatingActionButton = {
            TaskListAddButton(
                onClick = { showAddDialog = true },
                modifier = modifier.padding(bottom = 90.dp, end = 10.dp)
            )
        }
    ) { padding ->
        Column(modifier = modifier.padding(padding).fillMaxSize()) {
            TaskListHeader()
            TaskListGreeting(context = context)

            if (taskList.isEmpty()) {
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "No tasks yet",
                        fontSize = 28.sp,
                        modifier = Modifier.padding(bottom = 100.dp)
                    )
                }
            } else {
                LazyColumn {
                    items(taskList) { task ->
                        TaskCard(
                            task = task,
                            onDelete = {
                                val id = task.id
                                if (id != null) {
                                    viewModel.removeTask(id) { success, msg ->
                                        Toast.makeText(context, "task deleted successfully", Toast.LENGTH_SHORT).show()
                                    }
                                } else {
                                    Toast.makeText(context, "Cannot delete task: ID is null", Toast.LENGTH_SHORT).show()
                                }
                            },
                            onEdit = {
                                if (it.id != null) {
                                    taskToEdit = it
                                    showEditDialog = true
                                } else {
                                    Toast.makeText(context, "Cannot edit task: ID is null", Toast.LENGTH_SHORT).show()
                                }
                            },
                            onComplete = {
                                val id = task.id
                                if (id != null) {
                                    viewModel.completeTask(id) { success, msg ->
                                        Toast.makeText(context, "task completed successfully", Toast.LENGTH_SHORT).show()
                                    }
                                    completedTaskId = id
                                } else {
                                    Toast.makeText(context, "Cannot complete task: ID is null", Toast.LENGTH_SHORT).show()
                                }
                                completedTaskTitle = task.title
                                completedTaskDesc = task.description
                                showShareDialog = true
                            }
                        )
                    }
                }
            }

            if (showAddDialog) {
                TaskListAddTaskDialog(
                    onDismiss = { showAddDialog = false },
                    onAddTask = { title, desc ->
                        viewModel.addTask(context, title, desc) { success, message ->
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                            if (success) showAddDialog = false
                        }
                    }
                )
            }

            if (showEditDialog && taskToEdit != null) {
                val safeTask = taskToEdit!!
                if (safeTask.id == null) {
                    Toast.makeText(context, "Error: Task ID is null", Toast.LENGTH_SHORT).show()
                    showEditDialog = false
                    taskToEdit = null
                } else {
                    TaskListEditDialog(
                        task = safeTask,
                        onDismiss = {
                            showEditDialog = false
                            taskToEdit = null
                        },
                        onUpdate = { newTitle, newDesc ->
                            viewModel.editTask(context, safeTask.id, newTitle, newDesc) { success, msg ->
                                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                                if (success) {
                                    showEditDialog = false
                                    taskToEdit = null
                                }
                            }
                        }
                    )
                }
            }

            if (showShareDialog) {
                TaskListShareDialog(
                    taskTitle = completedTaskTitle,
                    onDismiss = { showShareDialog = false },
                    onSharePost = {
                        showShareDialog = false
                        navController.navigate("SharePostScreen/${completedTaskTitle}/${completedTaskDesc}")
                    },
                    onShareStory = {
                        imagePickerLauncher.launch("image/*")
                        showShareDialog = false
                    }
                )
            }
        }
    }
}
