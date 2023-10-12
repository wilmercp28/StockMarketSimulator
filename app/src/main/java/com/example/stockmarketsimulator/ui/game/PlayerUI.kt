package com.example.stockmarketsimulator.ui.game

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stockmarketsimulator.funtions.Bank
import com.example.stockmarketsimulator.funtions.Player
import java.text.DecimalFormat

@Composable
fun PlayerUI(player: Player, banks: SnapshotStateList<Bank>) {
    val format = DecimalFormat("#.##")
    val incomeTax = remember(player.totalYearEarnings.value) { getIncomeTax(player) }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text("Welcome ${player.playerName}", fontSize = 20.sp)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "This Year")
        Box(modifier = Modifier
            .border(1.dp,MaterialTheme.colorScheme.primary)){
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(10.dp)
            ) {
                Text(text = "Tax rate ${player.incomeTax.value}%")
                Text(text = "Income ${format.format(player.totalYearEarnings.value)}")
                Text(text = "IncomeTax ${format.format(incomeTax)}")
            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}
fun getIncomeTax(player: Player): Double {
    val income = player.totalYearEarnings.value
    val taxRate = player.incomeTax.value
    return income * (taxRate / 100.0)
}