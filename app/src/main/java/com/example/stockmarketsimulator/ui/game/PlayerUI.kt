package com.example.stockmarketsimulator.ui.game

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stockmarketsimulator.data.YearSummaryData
import com.example.stockmarketsimulator.funtions.Bank
import com.example.stockmarketsimulator.funtions.Player
import java.text.DecimalFormat

@Composable
fun PlayerUI(
    player: Player,
    banks: SnapshotStateList<Bank>,
    yearlySummaryList: SnapshotStateList<YearSummaryData>,
    gameStart: MutableState<Boolean>
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Welcome ${player.playerName}", fontSize = 30.sp)
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(10.dp)
                .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(20.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "This Year", textAlign = TextAlign.Center)
            Divider(thickness = 10.dp)
            Text(text = "Tax rate ${player.incomeTax.value}%")
            Text(text = "Income ${player.totalYearEarnings.value}")
            Text(text = "IncomeTax ${getIncomeTax(player)}")
            Button(onClick = { gameStart.value = false}) {
                Text(text = "Main Menu")

            }
        }
        Divider(thickness = 5.dp)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (yearlySummaryList.isNotEmpty()) {
                items(yearlySummaryList.reversed()) { year ->
                    YearSummary(year)
                }
            }
        }
    }
}
fun getIncomeTax(player: Player): Double {
    val format = DecimalFormat("#.##")
    val income = player.totalYearEarnings.value
    val taxRate = player.incomeTax.value
    return format.format(income * (taxRate / 100.0)).toDouble()
}

@Composable
fun YearSummary(
    summary: YearSummaryData
) {
    val format = DecimalFormat("#.##")
    Box(
        modifier = Modifier
            .padding(10.dp)
            .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(20.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "${summary.date}")
            Divider(thickness = 10.dp)
            Text(text = "Tax rate ${summary.taxRate}%")
            Text(text = "Income ${format.format(summary.income)}")
            Text(text = "IncomeTax ${summary.incomeTax}")
        }
    }
}
