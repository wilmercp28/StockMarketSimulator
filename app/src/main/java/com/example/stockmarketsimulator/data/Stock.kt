package com.example.stockmarketsimulator.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList

data class Stock(
    val name: String,
    val abbreviateName: String,
    val volatility: Double,
    var price: MutableState<Double>,
    var demand: Double = 100.0,
    var supply: Double = 99.0,
    var inEvent: Boolean = false,
    var shares: MutableState<Int> = mutableStateOf(0),
    var pastMonthPrice: MutableState<Double> = mutableStateOf(0.0),
    var percentageChange: MutableState<Int> = mutableStateOf(0),
    var totalPaidForShares: MutableState<Double> = mutableStateOf(0.0)
)

fun getInitialStocks(): SnapshotStateList<Stock> {
    return mutableStateListOf(
        Stock("Apple Inc.", "AAPL", 2.0, mutableStateOf(150.0)),
        Stock("Google LLC", "GOOGL", 3.0, mutableStateOf(2800.0)),
        Stock("Microsoft Corporation", "MSFT", 1.0, mutableStateOf(300.0)),
        Stock("Amazon.com, Inc.", "AMZN", 4.0, mutableStateOf(3400.0)),
        Stock("Tesla, Inc.", "TSLA", 5.0, mutableStateOf(750.0)),
        Stock("Facebook, Inc.", "FB", 2.0, mutableStateOf(350.0)),
        Stock("Netflix, Inc.", "NFLX", 3.0, mutableStateOf(550.0)),
        Stock("NVIDIA Corporation", "NVDA", 4.0, mutableStateOf(225.0)),
        Stock("PayPal Holdings, Inc.", "PYPL", 2.0, mutableStateOf(250.0)),
        Stock("Walt Disney Company", "DIS", 3.0, mutableStateOf(170.0)),
        Stock("Alphabet Inc.", "GOOG", 3.0, mutableStateOf(2700.0)),
        Stock("Visa Inc.", "V", 2.0, mutableStateOf(230.0)),
        Stock("Mastercard Incorporated", "MA", 2.0, mutableStateOf(350.0)),
        Stock("JPMorgan Chase & Co.", "JPM", 4.0, mutableStateOf(160.0)),
        Stock("Goldman Sachs Group, Inc.", "GS", 5.0, mutableStateOf(380.0)),
        Stock("Facebook Meta Platforms, Inc.", "META", 3.0, mutableStateOf(330.0)),
        Stock("Twitter, Inc.", "TWTR", 4.0, mutableStateOf(50.0)),
        Stock("Snap Inc.", "SNAP", 5.0, mutableStateOf(75.0)),
        Stock("Shopify Inc.", "SHOP", 3.0, mutableStateOf(1500.0)),
        Stock("Salesforce.com, Inc.", "CRM", 2.0, mutableStateOf(260.0)),
        Stock("Adobe Inc.", "ADBE", 2.0, mutableStateOf(650.0))
    )
}