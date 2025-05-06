package com.example.momentum_app.view.tasklistscreen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.momentum_app.model.Task
import com.example.momentum_app.viewmodel.TaskListViewModel

@Composable
fun TaskListScreen(
    viewModel: TaskListViewModel,
    context: Context
) {
    var showAddDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }
    var taskToEdit by remember { mutableStateOf<Task?>(null) }
    var showShareDialog by remember { mutableStateOf(false) }
    var completedTaskTitle by remember { mutableStateOf("") }
    var taskList by remember { mutableStateOf<List<Task>>(emptyList()) }

    LaunchedEffect(Unit) {
        viewModel.showAllTask() { success, list ->
            if (success && list != null) {
                taskList = list
            }
        }
    }

    Scaffold(
        containerColor = Color.White,
        floatingActionButton = {
            TaskListAddButton(
                onClick = { showAddDialog = true },
                modifier = Modifier.padding(bottom = 90.dp, end = 10.dp)
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            TaskListHeader()
            TaskListGreeting(context = context)

            if (taskList.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "No tasks yet",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.W300,
                        modifier = Modifier.padding(bottom = 100.dp)
                    )
                }
            } else {
                LazyColumn {
                    items(taskList) { task ->
                        TaskCard(
                            task = task,
                            onDelete = {
                                task.id?.let { id ->
                                    viewModel.removeTask(id) { success, msg ->
                                        if (success) {
                                            taskList = taskList.filter { it.id != id }
                                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                } ?: Toast.makeText(context, "Task ID is null", Toast.LENGTH_SHORT).show()
                            },
                            onEdit = {
                                taskToEdit = it
                                showEditDialog = true
                            },
                            onComplete = {
                                completedTaskTitle = task.title
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
                        viewModel.addTask(title, desc) { success, message ->
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                            if (success) {
                                viewModel.showAllTask() { _, list ->
                                    if (list != null) {
                                        taskList = list
                                    }
                                }
                            }
                        }
                    }
                )
            }

            if (showEditDialog && taskToEdit != null) {
                TaskListEditDialog(
                    task = taskToEdit!!,
                    onDismiss = {
                        showEditDialog = false
                        taskToEdit = null
                    },
                    onUpdate = { newTitle, newDesc ->
                        taskToEdit?.id?.let { id ->
                            viewModel.editTask(id, newTitle, newDesc) { success, msg ->
                                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                                if (success) {
                                    viewModel.showAllTask() { _, list ->
                                        if (list != null) {
                                            taskList = list
                                        }
                                        showEditDialog = false
                                        taskToEdit = null
                                    }
                                }
                            }
                        } ?: Toast.makeText(context, "Cannot edit: Task ID is null", Toast.LENGTH_SHORT).show()
                    }
                )
            }

            if (showShareDialog) {
                TaskListShareDialog(
                    taskTitle = completedTaskTitle,
                    onDismiss = { showShareDialog = false },
                    onSharePost = {
                        showShareDialog = false
                        Toast.makeText(context, "Shared as Post!", Toast.LENGTH_SHORT).show()
                    },
                    onShareStory = {
                        showShareDialog = false
                        Toast.makeText(context, "Shared as Story!", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }
}