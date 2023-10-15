package com.example.stockmarketsimulator.funtions

import android.util.Log
import com.example.stockmarketsimulator.data.Date
import com.example.stockmarketsimulator.data.Stock
import java.text.DecimalFormat
import kotlin.random.Random


fun pricesUpdate(stocksList: List<Stock>, date: Date) {
    val format = DecimalFormat("#.##")
    val rateSupplyReachDemand = 30
    val volatilitySensibility = 0.9
    val maxRandomChange = 0.2
    for (stock in stocksList) {
        if (date.day.value == 1 && date.month.value % 4 == 0) {
            val randomDemand = Random.nextInt(10)
            val posOrNegRandom = Random.nextInt(2)
            if (posOrNegRandom == 0) stock.demand += randomDemand else stock.demand -= randomDemand
        }
        val supplyDemandRatio = (stock.demand - stock.supply) / stock.supply
        val priceScalingFactor = stock.price.value / 100
        val scaledSupplyDemandRatio = supplyDemandRatio * priceScalingFactor
        val priceChange =
            scaledSupplyDemandRatio * volatilitySensibility + (Math.random() * maxRandomChange - maxRandomChange / 2)
        val newPrice = stock.price.value + priceChange
        if (newPrice <= 0){
            stock.price.value = 1.0
        } else {
            stock.price.value = format.format(newPrice).toDouble()
        }
        val supplyDemandDifference = (stock.demand - stock.supply) / rateSupplyReachDemand
        stock.supply += supplyDemandDifference
        Log.d(
            stock.name,
            "Demand ${stock.demand}, Supply ${stock.supply}, Price ${stock.price.value}"
        )
        if (date.day.value == 1 && date.month.value == 1) {
            stock.pastYearPrice.value = stock.price.value
            stock.percentageChange.value = 0
        } else {
            stock.percentageChange.value =
                (((stock.price.value - stock.pastYearPrice.value) / stock.pastYearPrice.value) * 100).toInt()
        }
    }
}



