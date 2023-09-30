package com.example.stockmarketsimulator.funtions

import android.util.Log
import androidx.compose.runtime.MutableState
import com.example.stockmarketsimulator.data.Stock
import java.text.DecimalFormat


fun pricesUpdate(stocksList: List<Stock>, day: MutableState<Int>) {
    for (stock in stocksList.indices) {
        if (day.value == 1){
            stocksList[stock].pastMonthPrice.value = stocksList[stock].price.value
        }
        val format = DecimalFormat("#.##")
        var basePrice = stocksList[stock].price.value
        val demand = stocksList[stock].demand
        var supply = stocksList[stock].supply
        val supplyDemandRatio = (demand - supply) / supply
        val newPrice = basePrice + supplyDemandRatio
        stocksList[stock].price.value = format.format(newPrice).toDouble()
        //supply adjustment
        stocksList[stock].supply += if (supply < demand) {
            val randomIncrease = Math.random() * 0.05
            randomIncrease
        } else {
            val randomIncrease = Math.random() * -0.05
            randomIncrease
        }
        stocksList[stock].percentageChange.value =
            (((newPrice - stocksList[stock].pastMonthPrice.value.toInt()) / stocksList[stock].pastMonthPrice.value.toInt()) * 100).toInt()


    }
    day.value++
    Log.d(stocksList[0].name,"Demand ${stocksList[0].demand},Supply ${stocksList[0].supply}")
}



