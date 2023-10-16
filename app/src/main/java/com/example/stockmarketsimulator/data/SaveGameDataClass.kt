package com.example.stockmarketsimulator.data

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.stockmarketsimulator.funtions.Bank
import com.example.stockmarketsimulator.funtions.Player

data class SaveGameDataClass(
    val slot: Int,
    val player: Player,
    val banks: List<Bank>,
    val stock: List<Stock>,
    val news: List<News>,
    val yearSummaryData: List<YearSummaryData>
)
