package com.example.stockmarketsimulator.funtions

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList

data class Bank(
    val bankName: String,
    var creditLimit: MutableState<Double>,
    var interestRate: MutableState<Int>
)

fun getInitialBanks(): SnapshotStateList<Bank> {
    return mutableStateListOf(
        Bank("Chase", mutableStateOf(10000.0), mutableStateOf(5)),
        Bank("BankOfAmerica", mutableStateOf(5000.0), mutableStateOf(8)),
        Bank("WellsFargo", mutableStateOf(2000.0), mutableStateOf(12))
    )
}