package com.example.stockmarketsimulator.funtions

import androidx.compose.runtime.MutableState
import com.example.stockmarketsimulator.data.Stock


fun canPlayerPay(
    balance: Double,
    cost: Double
): Boolean {
    return balance >= cost
}


fun buyStock(
    sharesCount: MutableState<Int>,
    stock: Stock,
    player: Player
) {
    val totalStockPrice = sharesCount.value * stock.price.value
    if (player.balance.value > totalStockPrice) {
        stock.shares.value += sharesCount.value
        player.balance.value -= totalStockPrice
        val totalInvested = stock.averageBuyPrice.value * stock.shares.value
        stock.averageBuyPrice.value = (totalInvested + totalStockPrice) / stock.shares.value
        sharesCount.value = 0

    }
}

fun sellStock(
    sharesCount: MutableState<Int>,
    stock: Stock,
    player: Player,
    gainLoses: Double
) {
    val totalStockPrice = sharesCount.value * stock.price.value
    if (stock.shares.value > 0){
        player.balance.value += totalStockPrice
        stock.shares.value -= sharesCount.value
        player.totalYearEarnings.value += gainLoses * sharesCount.value
        sharesCount.value = 0
    }
}