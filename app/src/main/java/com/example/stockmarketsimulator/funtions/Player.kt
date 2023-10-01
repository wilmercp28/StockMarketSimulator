package com.example.stockmarketsimulator.funtions

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotMutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.stockmarketsimulator.data.Stock

data class Player(
val playerName: String,
var balance: MutableState<Double>,
var loan: MutableState<Double>,
)
fun getInitialPlayer(): Player {
    return Player("Wilmer", mutableStateOf(5000.00), mutableStateOf(500.00))
}


