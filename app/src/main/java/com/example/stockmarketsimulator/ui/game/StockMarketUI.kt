package com.example.stockmarketsimulator.ui.game

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stockmarketsimulator.data.Stock
import com.example.stockmarketsimulator.funtions.Player
import com.example.stockmarketsimulator.funtions.buyStock
import com.example.stockmarketsimulator.funtions.sellStock
import java.text.DecimalFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StocksList(stocks: List<Stock>, player: Player) {
    LazyColumn {
        items(stocks) { stock ->
            StockItem(stock = stock, player)
            Divider(modifier = Modifier.padding(horizontal = 10.dp))
        }
        item {
            var text = remember { mutableStateOf("") }
            TextField(value = text.value,
                onValueChange = { text.value = it })
            IconButton(onClick = {
                for (stock in stocks.indices) {
                    stocks[stock].demand = text.value.toDouble()
                }
            }) {
                Icon(Icons.Filled.Add, contentDescription = "ss")
            }
        }
    }
}

@Composable
fun StockItem(
    stock: Stock,
    player: Player
) {
    val format = DecimalFormat("#.##")
    var selling by remember { mutableStateOf(false) }
    var buying by remember { mutableStateOf(false) }
    val expanded = remember { mutableStateOf(false) }
    val sharesCount = remember { mutableStateOf(stock.shares.value) }
    val stockNameTextSIze = 20
    var gainLoses by remember(stock.price.value,sharesCount.value) { mutableStateOf(getGainOrLoses(stock,sharesCount)) }

    Column(
        modifier = Modifier
            .clickable {
                expanded.value = !expanded.value
            }
            .fillMaxWidth()
            .animateContentSize()
            .padding(10.dp)
    ) {
        Text(
            text = if (!expanded.value) stock.abbreviateName else stock.name,
            textAlign = if (expanded.value) TextAlign.Center else TextAlign.Center,
            fontSize = if (expanded.value) (stockNameTextSIze - 2).sp else stockNameTextSIze.sp,
            modifier = Modifier
                .animateContentSize()
        )
        Row(
            modifier = Modifier
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (!expanded.value) 
                Column {
                    Text(text = "Shares: ${stock.shares.value}",)
                    if (stock.shares.value != 0) Text(text = "Average buy price ${stock.averageBuyPrice.value}")
                }
            Spacer(modifier = Modifier.weight(1f))
            Text(text = " ${stock.percentageChange.value}%")
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "$${stock.price.value}")
        }
        if (expanded.value) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Row() {
                    Text(text = "Last year Price: ${stock.pastMonthPrice.value}")
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    AnimatedVisibility(!buying && !selling) {
                        Column {
                            Text(text = "Owned shares: ${stock.shares.value}")
                            if (stock.shares.value != 0) {

                            }
                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    AnimatedVisibility(!buying && !selling) {
                        Button(onClick = {
                            buying = true
                            sharesCount.value = 0
                        }) {
                            Text(text = "Buy")
                        }
                    }
                    AnimatedVisibility(buying || selling) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row {
                                Button(onClick = {
                                    buying = false
                                    selling = false
                                }) {
                                    Text(text = "Cancel")
                                }
                                Spacer(modifier = Modifier.weight(1f))
                                IconButton(onClick = {
                                    if (sharesCount.value > 0)
                                        sharesCount.value--
                                }) {
                                    Icon(
                                        Icons.Filled.KeyboardArrowDown,
                                        contentDescription = "Shares--"
                                    )
                                }
                                Spacer(modifier = Modifier.weight(1f))
                                Text(text = sharesCount.value.toString())
                                Spacer(modifier = Modifier.weight(1f))
                                IconButton(onClick = {
                                    if (sharesCount.value < stock.shares.value || buying) sharesCount.value++
                                }) {
                                    Icon(
                                        Icons.Filled.KeyboardArrowUp,
                                        contentDescription = "Shares++"
                                    )
                                }
                                Spacer(modifier = Modifier.weight(1f))
                                Button(onClick = {
                                    if (buying) {
                                        buyStock(sharesCount, stock, player)
                                        buying = false
                                        sharesCount.value = 0
                                    } else {
                                        sellStock(sharesCount, stock, player)
                                        selling = false
                                        sharesCount.value = 0
                                    }
                                }) {
                                    Text(if (selling) "Sell" else "Buy")

                                }
                            }
                            Text("Total = ${format.format(stock.price.value * sharesCount.value)}", fontSize = 10.sp)
                            if (selling && sharesCount.value  != 0) {
                                Text(
                                    "Profit/Loses = ${format.format(gainLoses * sharesCount.value)}",
                                    fontSize = 10.sp,
                                    color = if (gainLoses > 0 || gainLoses == 0.0) Color.Green else Color.Red
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    AnimatedVisibility(!buying && !selling && stock.shares.value != 0) {
                        Button(onClick = {
                            selling = true
                            sharesCount.value = 0
                        }) {
                            Text(text = "Sell")
                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

fun getGainOrLoses(stock: Stock, sharesCount: MutableState<Int>): Double {
    val totalInvested = stock.averageBuyPrice.value * stock.shares.value
    val totalIfSoldAll = stock.price.value * stock.shares.value
    val difference =  totalIfSoldAll - totalInvested
    return difference / stock.shares.value
}