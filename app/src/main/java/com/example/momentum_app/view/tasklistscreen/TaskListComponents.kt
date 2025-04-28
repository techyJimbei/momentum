package com.example.momentum_app.view.tasklistscreen

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TaskListComponents(
    modifier: Modifier = Modifier,
    header: (@Composable () -> Unit)? = null,
    greeting: String,
    notes: (LazyListScope.() -> Unit)? = null,
    addButton: (@Composable () -> Unit)? = null
){

}