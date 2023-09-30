package com.example.stockmarketsimulator.ui.game

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.stockmarketsimulator.data.Stock

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StocksList(stocks: List<Stock>) {
    LazyColumn {
        items(stocks) { stock ->
            StockItem(stock = stock)
            Divider(modifier = Modifier.padding(horizontal = 10.dp))
        }
        item {
            var text = remember { mutableStateOf("") }
            TextField(value = text.value,
                onValueChange = {text.value = it}  )
            IconButton(onClick = {
                for (stock in stocks.indices) {
                    stocks[stock].demand = text.value.toDouble()
                }
            }) {
                Icon(Icons.Filled.Add, contentDescription ="ss" )
            }
        }
    }
}
@Composable
fun StockItem(
    stock: Stock,
) {
    val expanded = remember { mutableStateOf(false) }
    val numberOfSharesToBuy = remember { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .clickable {
                expanded.value = !expanded.value
            }
            .fillMaxWidth()
            .animateContentSize()
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Text(
                text = if (!expanded.value) stock.abbreviateName else stock.name,
                textAlign = if (expanded.value) TextAlign.Center else TextAlign.Start
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(text =" ${stock.percentageChange.value}%")
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "$${stock.price.value}")
        }
        if (expanded.value) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row() {
                    Text(text = "Last year Price: ${stock.pastMonthPrice.value}")
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Owned shares: ${stock.shares}")
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = {
                        if (numberOfSharesToBuy.value != 0) {
                            numberOfSharesToBuy.value--
                        }
                    }) {
                        Icon(Icons.Filled.Delete, "Shares-")
                    }
                    Text(text = numberOfSharesToBuy.value.toString())
                    IconButton(onClick = {
                            numberOfSharesToBuy.value++
                    }) {
                        Icon(Icons.Filled.Add, "Shares+")
                    }
                }
            }
        }
    }
}