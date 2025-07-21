package com.example.momentum_app.view.tasklistscreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.momentum_app.model.Task
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.ui.text.style.TextDecoration

@Composable
fun TaskCard(
    task: Task,
    onDelete: () -> Unit,
    onEdit: (Task) -> Unit,
    onComplete: () -> Unit
) {
    var isChecked by remember { mutableStateOf(task.isCompleted) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = {
                    if (!isChecked) {
                        isChecked = it
                        if (it) onComplete()
                    }
                },
                enabled = !isChecked
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        textDecoration = if (isChecked) TextDecoration.LineThrough else TextDecoration.None
                    )
                )
                if (task.description.isNotEmpty()) {
                    Text(
                        text = task.description,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            textDecoration = if (isChecked) TextDecoration.LineThrough else TextDecoration.None
                        )
                    )
                }
            }


            Text(
                text = "Created: ${formatTimestamp(task.createdAt)}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                modifier = Modifier.padding(top = 4.dp)
            )

            IconButton(onClick = { if (!isChecked) onEdit(task) }, enabled = !isChecked) {
                Icon(Icons.Default.Edit, contentDescription = "Edit")
            }

            IconButton(onClick = { if (!isChecked) onDelete() }, enabled = !isChecked) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}

fun formatTimestamp(timestamp: Long): String {
    val sdf = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
    return sdf.format(Date(timestamp))
}
