package com.example.stockmarketsimulator.funtions

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.stockmarketsimulator.R
import com.example.stockmarketsimulator.data.getInitialStocks
import com.example.stockmarketsimulator.ui.game.BankScreen
import com.example.stockmarketsimulator.ui.game.StocksList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameStockMarket(

) {
    var selectedOption = remember { mutableStateOf("Market") }
    val day = remember { mutableStateOf(1) }
    val paused = remember { mutableStateOf(false) }
    val stocks = remember { getInitialStocks() }
    val banks = remember { getInitialBanks() }
    val player = remember { getInitialPlayer() }
    val bankIcon: Painter = painterResource(id = R.drawable.account_balance_fill0_wght400_grad0_opsz24)
    Update(1000, paused) {
        pricesUpdate(stocks, day)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row() {
                        TopBarIcons(
                            icon = rememberVectorPainter(image = Icons.Default.ShoppingCart),
                            selected = selectedOption.value == "Market"
                        ) {
                            selectedOption.value = "Market"
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        TopBarIcons(
                            icon = painterResource(id = R.drawable.account_balance_fill0_wght400_grad0_opsz24),
                            selected = selectedOption.value == "Bank"
                        ) {
                            selectedOption.value = "Bank"
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        TopBarIcons(
                            icon = rememberVectorPainter(image = Icons.Default.ShoppingCart),
                            selected = selectedOption.value == "Market"
                        ) {
                            selectedOption.value = "Market"
                        }
                    }
                }

            )
        }

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            AnimatedVisibility(selectedOption.value == "Market") {
                StocksList(stocks)
            }
            AnimatedVisibility(selectedOption.value == "Bank") {
                BankScreen(banks,player)
            }
        }

    }
}

@Composable
fun TopBarIcons(
    icon: Painter,
    selected: Boolean,
    onSelect: () -> Unit
) {
    Column(
    ) {
        IconButton(
            onClick = onSelect,
            content = {
                Icon(
                    painter = icon,
                    contentDescription = null,
                    tint = if (selected) MaterialTheme.colorScheme.primary else Color.Gray,
                    modifier = Modifier
                        .size(100.dp)
                )
            }
        )
    }
}