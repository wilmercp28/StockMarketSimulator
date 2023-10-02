package com.example.stockmarketsimulator.funtions

import androidx.compose.runtime.MutableState
import com.example.stockmarketsimulator.data.Stock


fun canPlayerPay(
    balance: Double,
    cost: Double
): Boolean {
    return balance >= cost
}

fun buyOrSell(
    stock: Stock,
    numberSelectedShares: MutableState<Int>,
    player: Player
) {
    if (stock.shares.value > numberSelectedShares.value) {
        sellStock(numberSelectedShares,stock,player)
    } else {
        buyStock(numberSelectedShares, stock, player)
    }
}

fun buyStock(
    sharesCount: MutableState<Int>,
    stock: Stock,
    player: Player
) {
    val totalCost = stock.price.value * sharesCount.value
    if (canPlayerPay(player.balance.value, totalCost)) {
        stock.shares.value += sharesCount.value
        player.balance.value -= totalCost
    }
}

fun sellStock(
    sharesCount: MutableState<Int>,
    stock: Stock,
    player: Player
) {
    if (stock.shares.value >= sharesCount.value){
        val sharesToSell = stock.shares.value - sharesCount.value
        stock.shares.value -= sharesToSell
        player.balance.value += stock.price.value * sharesToSell
    }
}