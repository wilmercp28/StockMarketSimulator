package com.example.stockmarketsimulator.ui.game

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.stockmarketsimulator.data.Stock

@Composable
fun StocksList(stocks: List<Stock>) {
    LazyColumn {
        items(stocks) { stock ->
            StockItem(stock = stock)
            Divider(modifier = Modifier.padding(horizontal = 16.dp))
        }
    }
}
@Composable
fun StockItem(
    stock: Stock,
   ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = stock.name)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "$${stock.price.value}")
    }
}