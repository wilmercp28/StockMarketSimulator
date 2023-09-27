package com.example.stockmarketsimulator.funtions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.stockmarketsimulator.data.getInitialStocks
import com.example.stockmarketsimulator.ui.game.StocksList

@Composable
fun GameStockMarket(

) {
    val paused = remember { mutableStateOf(false) }
    val stocks = remember { getInitialStocks() }
    Update(1000,paused) {

    }
    StocksList(stocks)



}