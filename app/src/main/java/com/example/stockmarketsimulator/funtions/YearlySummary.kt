package com.example.stockmarketsimulator.funtions

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.stockmarketsimulator.data.Date
import com.example.stockmarketsimulator.data.YearSummaryData
import com.example.stockmarketsimulator.ui.game.getIncomeTax

fun yearlySummaryToList(
    date: Date,
    player: Player,
    yearlySummaryList: SnapshotStateList<YearSummaryData>
) {
    if (date.year.value != 1){
        if (date.day.value == 1 && date.month.value == 1){
            val date = "Year ${date.year.value - 1}"
            yearlySummaryList += YearSummaryData(date,player.totalYearEarnings.value,getIncomeTax(player),player.incomeTax.value)
            player.totalYearEarnings.value = 0.0
        }
    }
}