package com.example.momentum_app.view.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HomeRow(
    modifier: Modifier = Modifier,
    header: (@Composable () -> Unit)? = null,
    stories: (@Composable () -> Unit)? = null,
    line: (@Composable () -> Unit)? = null,
    posts: (LazyListScope.() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.Center,
            contentPadding = PaddingValues(vertical = 4.dp)
        ) {
            item {
                header?.invoke()
            }
            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        stories?.invoke()
                    }
                }
            }
            item {
                line?.invoke()
            }
            posts?.invoke(this)
        }
    }
}
