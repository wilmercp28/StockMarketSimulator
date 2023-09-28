package com.example.stockmarketsimulator.funtions

import android.util.Log
import com.example.stockmarketsimulator.data.Stock
import org.apache.commons.math3.distribution.NormalDistribution
import java.lang.Math.round
import java.text.DecimalFormat
import kotlin.math.roundToInt
import kotlin.random.Random


fun pricesUpdate(stocksList: List<Stock>) {
    for (stock in stocksList.indices) {
        val expectedDailyReturn = Random.nextDouble(0.0, 0.01)
        val volatility = stocksList[stock].volatility
        val randomValue = NormalDistribution(1.0, volatility).sample()
        val dailyReturn =
            (expectedDailyReturn - (volatility * volatility) / 2) + volatility * randomValue
        val dailyPriceChange = kotlin.math.exp(dailyReturn)
        stocksList[stock].price.value *= dailyPriceChange
        stocksList[stock].price.value = (stocksList[stock].price.value * 100).roundToInt() / 100.0

        Log.d(stocksList[stock].name, stocksList[stock].price.toString())
    }
}



