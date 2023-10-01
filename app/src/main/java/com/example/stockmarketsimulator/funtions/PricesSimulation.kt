package com.example.stockmarketsimulator.funtions

import android.util.Log
import androidx.compose.runtime.MutableState
import com.example.stockmarketsimulator.data.Stock
import java.text.DecimalFormat


fun pricesUpdate(stocksList: List<Stock>, day: MutableState<Int>) {
    val rateSupplyReachDemand = 30 // higher Faster
    val volatilitySensibility = 0.01

    for (stock in stocksList) {
        if (day.value == 1){
            stock.pastMonthPrice.value = stock.price.value
        }
        val format = DecimalFormat("#.##")

        val supplyDemandRatio = (stock.demand - stock.supply) / stock.supply
        val newPrice = stock.price.value + supplyDemandRatio

        stock.price.value = format.format(newPrice).toDouble()
        val demandChangeFactor = stock.volatility * volatilitySensibility
        stock.demand += (Math.random() * 0.05 * demandChangeFactor) - 0.025 * demandChangeFactor
        val supplyDemandDifference = (stock.demand - stock.supply) / rateSupplyReachDemand
        stock.supply += supplyDemandDifference
        Log.d("aa",supplyDemandDifference.toString())
        stock.supply += (Math.random() * 0.05) - 0.025
        stock.percentageChange.value = (((newPrice - stock.pastMonthPrice.value) / stock.pastMonthPrice.value) * 100).toInt()

    }
    day.value++
    Log.d(stocksList[0].name,"Demand ${stocksList[0].demand},Supply ${stocksList[0].supply}")
}



