package com.example.stockmarketsimulator.ui.game

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stockmarketsimulator.R
import com.example.stockmarketsimulator.data.Date
import com.example.stockmarketsimulator.data.News
import com.example.stockmarketsimulator.data.Stock
import com.example.stockmarketsimulator.funtions.Player

@Composable
fun MailUI(
    stocks: SnapshotStateList<Stock>,
    paused: MutableState<Boolean>,
    player: Player,
    newsList: MutableList<News>,
    date: Date,
) {
    var expanded = remember { mutableStateOf(false) }
    var selectedNews by remember { mutableStateOf(newsList[0]) }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AnimatedVisibility(expanded.value) {
            NewsDetails(selectedNews, expanded)
        }
        AnimatedVisibility(!expanded.value) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    item {
                        Text(text = "Gmail", fontSize = 40.sp)
                    }
                    items(newsList.reversed()) { news ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    selectedNews = news
                                    selectedNews.read = true
                                    expanded.value = true
                                }
                                .background(if (news.read) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.primary),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = news.title, fontSize = 20.sp)
                            Text(text = news.date, fontSize = 20.sp)
                            Divider(thickness = 5.dp)
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun NewsDetails(
    selectedNews: News,
    expanded: MutableState<Boolean>
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            IconButton(onClick = { expanded.value = false }) {
                Icon(Icons.Outlined.KeyboardArrowLeft, contentDescription = "Back")
            }
            Text(text = selectedNews.title, fontSize = 20.sp)
            Spacer(modifier = Modifier.weight(1f))
        }
        Divider(thickness = 10.dp)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = selectedNews.message, fontSize = 20.sp)
        Spacer(modifier = Modifier.weight(3f))
        Text(text = selectedNews.date)
    }
}

