package com.example.stockmarketsimulator.ui.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MonotonicFrameClock
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stockmarketsimulator.funtions.Bank
import com.example.stockmarketsimulator.funtions.Player

@Composable
fun BankScreen(banks: SnapshotStateList<Bank>, player: Player) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)
        ) {
            LazyRow(
                content = {
                    items(banks) { bank ->
                        BankDetails(bank)
                    }
                }
            )
        }
    }
}

@Composable
fun BankDetails(bank: Bank) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(LocalConfiguration.current.screenWidthDp.dp)
            .padding(5.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)
                .padding(5.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = bank.bankName,
                fontSize = 40.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(5.dp)
            )
            Text(
                text = "Loans interest ${bank.interestRate.value}%",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(5.dp)
            )
            Text(
                text = "Balance: ${bank.debt.value}",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(5.dp)
            )
        }
    }
}