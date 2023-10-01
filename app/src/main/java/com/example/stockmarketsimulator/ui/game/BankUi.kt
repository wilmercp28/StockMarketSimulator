package com.example.stockmarketsimulator.ui.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MonotonicFrameClock
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stockmarketsimulator.funtions.Bank
import com.example.stockmarketsimulator.funtions.Player

@Composable
fun BankScreen(banks: SnapshotStateList<Bank>, player: Player) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            for (bank in banks) {
                BankDetails(bank)
            }
            Spacer(modifier = Modifier.weight(1f))
        }

    }
}

@Composable
fun BankDetails(bank: Bank) {
    Column(
        modifier = Modifier
    ) {
        Text(text = bank.bankName, fontSize = 20.sp)

    }
}