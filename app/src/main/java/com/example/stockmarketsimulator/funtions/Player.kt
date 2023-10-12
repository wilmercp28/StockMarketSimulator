package com.example.stockmarketsimulator.funtions

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Player(
    val playerName: String,
    var balance: MutableState<Double>,
    var totalYearEarnings: MutableState<Double>,
    var incomeTax: MutableState<Double>
)
fun getInitialPlayer(): Player {
    return Player("Wilmer", mutableStateOf(5000.00), mutableStateOf(0.0), mutableStateOf(12.0))
}


