package com.example.stockmarketsimulator.ui.game

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stockmarketsimulator.data.Date
import com.example.stockmarketsimulator.funtions.Bank
import com.example.stockmarketsimulator.funtions.Player

@Composable
fun BankScreen(
    banks: SnapshotStateList<Bank>,
    player: Player,
    date: Date,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyRow(
                content = {
                    items(banks) { bank ->
                        BankDetails(bank, player,date)
                    }
                }
            )
        }
    }
}

@Composable
fun BankDetails(bank: Bank, player: Player, date: Date) {
    val interestAmount = (bank.interestRate.value / 100.0) * bank.debt.value
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(LocalConfiguration.current.screenWidthDp.dp)
            .padding(5.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(20.dp)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(3f))
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
            AnimatedVisibility(bank.debt.value == 0.0) {
                Text(
                    text = "Loan Amount: ${bank.creditLimit.value}",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(5.dp)
                )
            }
            AnimatedVisibility(bank.debt.value != 0.0) {
                Text(
                    text = "Balance: ${bank.debt.value}\n" +
                            "PaymentsLeft ${bank.loanPaymentsLeft.value}\n" +
                            "PayDay ${bank.paymentDay.value}\n" +
                            "Principal | interest\n" +
                            "200 | $interestAmount",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(5.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            AnimatedVisibility(bank.debt.value == 0.0) {
                Button(
                    onClick = {
                        player.balance.value += bank.creditLimit.value
                        bank.debt.value = bank.creditLimit.value
                        bank.paymentDay.value = date.day.value
                        bank.loanPaymentsLeft.value = bank.creditLimit.value.toInt() / 200
                    },
                    modifier = Modifier.border(
                        5.dp, MaterialTheme.colorScheme.onPrimary,
                        RoundedCornerShape(20.dp)
                    )
                ) {
                    Text("Take Loan")
                }
            }
            AnimatedVisibility(bank.debt.value != 0.0) {
                Button(
                    onClick = {
                              if (player.balance.value > bank.debt.value){
                                  player.balance.value -= bank.debt.value
                                  bank.debt.value = 0.0
                              }
                    },
                    modifier = Modifier.border(
                        5.dp, MaterialTheme.colorScheme.onPrimary,
                        RoundedCornerShape(20.dp)
                    )
                ) {
                    Text("Payoff")
                }
            }
            Spacer(modifier = Modifier.weight(3f))
        }

    }
}