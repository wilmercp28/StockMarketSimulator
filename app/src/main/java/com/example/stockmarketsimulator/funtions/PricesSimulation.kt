package com.example.stockmarketsimulator.funtions

import android.util.Log
import androidx.compose.runtime.MutableState
import com.example.stockmarketsimulator.data.Stock
import java.text.DecimalFormat
import java.util.Random


fun pricesUpdate(stocksList: List<Stock>, day: MutableState<Int>, month: MutableState<Int>) {
    val format = DecimalFormat("#.##")
    val rateSupplyReachDemand = 30
    val volatilitySensibility = 0.9
    val maxRandomChange = 0.2
    for (stock in stocksList) {
        val supplyDemandRatio = (stock.demand - stock.supply) / stock.supply
        val priceScalingFactor = stock.price.value / 100
        val scaledSupplyDemandRatio = supplyDemandRatio * priceScalingFactor
        val priceChange = scaledSupplyDemandRatio * volatilitySensibility + (Math.random() * maxRandomChange - maxRandomChange / 2)
        val newPrice = stock.price.value + priceChange
        stock.price.value = format.format(newPrice).toDouble()
        val supplyDemandDifference = (stock.demand - stock.supply) / rateSupplyReachDemand
        stock.supply += supplyDemandDifference
        Log.d(stock.name, "Demand ${stock.demand}, Supply ${stock.supply}, Price ${stock.price.value}")
        if (day.value == 1 && month.value == 1 ) {
            stock.pastYearPrice.value = stock.price.value
            stock.percentageChange.value = 0
        } else {
            stock.percentageChange.value =
               ( ((stock.price.value - stock.pastYearPrice.value) / stock.pastYearPrice.value) * 100).toInt()
        }
    }
}



