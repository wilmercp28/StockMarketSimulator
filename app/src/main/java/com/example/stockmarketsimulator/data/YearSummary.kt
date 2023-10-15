package com.example.stockmarketsimulator.data

import android.provider.ContactsContract.Data
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

data class YearSummaryData(
    val date: String,
    val income: Double,
    val incomeTax: Double,
    val taxRate: Double
)

fun getInitialYearSummaryList(): SnapshotStateList<YearSummaryData> {
    return mutableStateListOf()
}
