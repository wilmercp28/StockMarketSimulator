package com.example.stockmarketsimulator.data

data class News(
    val title: String,
    val stockName: String = "",
    val date: String,
    val message: String,
    var read: Boolean  = false
)
