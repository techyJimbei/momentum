package com.example.momentum_app.view.sharingtask


import android.content.Context
import android.net.Uri
import android.util.Base64
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.momentum_app.viewmodel.PostViewModel
import java.io.InputStream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SharePostScreen(
    context: Context,
    viewModel: PostViewModel,
    navController: NavController,
    taskTitle: String,
    taskDescription: String
) {

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var caption by remember { mutableStateOf("") }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? -> imageUri = uri }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Share Achievement") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(onClick = { imagePickerLauncher.launch("image/*") }) {
                Text("Pick Image")
            }

            imageUri?.let {
                Image(
                    painter = rememberAsyncImagePainter(it),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }

            OutlinedTextField(
                value = caption,
                onValueChange = { caption = it },
                label = { Text("Caption") },
                modifier = Modifier.fillMaxWidth()
            )

            Text(text = "Task: $taskTitle\n$taskDescription")

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    if (imageUri == null) {
                        Toast.makeText(context, "Please select an image", Toast.LENGTH_SHORT).show()
                    } else {
                        val base64 = convertImageToBase64(context, imageUri!!)
                        viewModel.createPost(
                            context,
                            base64,
                            caption,
                            taskTitle,
                            taskDescription
                        ) { success, msg ->
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                            if (success) {
                                navController.popBackStack() // go back to task list
                            }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Post")
            }
        }
    }
}

fun convertImageToBase64(context: Context, uri: Uri): String {
    val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
    val bytes = inputStream?.readBytes()
    return if (bytes != null) Base64.encodeToString(bytes, Base64.DEFAULT) else ""
}
