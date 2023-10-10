package com.example.stockmarketsimulator.funtions

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.stockmarketsimulator.data.Date

data class Bank(
    val bankName: String,
    var creditLimit: MutableState<Double>,
    var interestRate: MutableState<Int>,
    var loanTerm: Int,
    var debt: MutableState<Double> = mutableStateOf(0.0),
    var loanPaymentsLeft: MutableState<Int> = mutableStateOf(0),
    var paymentDay: MutableState<Int> = mutableStateOf(0),
)

fun getInitialBanks(): SnapshotStateList<Bank> {
    return mutableStateListOf(
        Bank("Chase", mutableStateOf(10000.0), mutableStateOf(5), 5),
        Bank("BankOfAmerica", mutableStateOf(5000.0), mutableStateOf(8), 5),
        Bank("WellsFargo", mutableStateOf(2000.0), mutableStateOf(12), 5)
    )
}

fun payInterest(banks: SnapshotStateList<Bank>, player: Player, date: Date) {
    for (bank in banks) {
        val interestAmount = (bank.interestRate.value / 100.0) * bank.debt.value
        if (date.day.value == bank.paymentDay.value && bank.debt.value != 0.0){
            player.balance.value -= (interestAmount + 200)
            bank.debt.value -= 200
            bank.loanPaymentsLeft.value--
        }
    }
}