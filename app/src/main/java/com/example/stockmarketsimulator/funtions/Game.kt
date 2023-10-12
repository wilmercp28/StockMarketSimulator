package com.example.stockmarketsimulator.funtions

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.stockmarketsimulator.R
import com.example.stockmarketsimulator.data.Date
import com.example.stockmarketsimulator.data.News
import com.example.stockmarketsimulator.data.getInitialStocks
import com.example.stockmarketsimulator.ui.game.BankScreen
import com.example.stockmarketsimulator.ui.game.MailUI
import com.example.stockmarketsimulator.ui.game.PlayerUI
import com.example.stockmarketsimulator.ui.game.StocksList
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameStockMarket(

) {
    var selectedOption = remember { mutableStateOf("Market") }
    val date = remember { Date() }
    val paused = remember { mutableStateOf(false) }
    val stocks = remember { getInitialStocks() }
    val news = remember {
        mutableStateListOf(
            News(
                "Welcome",
                "",
                "Day 1 Month 1 Year 1",
                "Welcome To The Game"
            )
        )
    }
    val banks = remember { getInitialBanks() }
    val player = remember { getInitialPlayer() }
    val unreadIcon = painterResource(id = R.drawable.mark_email_unread_fill0_wght400_grad0_opsz24)
    val readIcon = painterResource(id = R.drawable.mark_email_read_fill0_wght400_grad0_opsz24)
    val mailIcon = remember { mutableStateOf(unreadIcon) }
    var hasUnreadEmails = remember { mutableStateOf(news.any { !it.read }) }
    Update(paused) {
        if (!paused.value) {
            pricesUpdate(stocks, date)
            calendar(date)
            payInterest(banks, player, date)
            newsFeedGenerator(stocks, news, date)
        }
        hasUnreadEmails.value = news.any { !it.read }
        mailIcon.value = if (hasUnreadEmails.value) unreadIcon else readIcon
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
                            icon = rememberVectorPainter(image = Icons.Default.AccountBox),
                            selected = selectedOption.value == "Player") {
                            selectedOption.value = "Player"

                        }
                        Spacer(modifier = Modifier.weight(1f))
                        TopBarIconMail(
                            icon = mailIcon.value,
                            selected = selectedOption.value == "Mail",
                            hasUnreadEmails
                        ) {
                            selectedOption.value = "Mail"
                        }
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                actions = {
                    Text(text = player.balance.value.toString())
                    Spacer(modifier = Modifier.weight(1f))
                    Button(onClick = { paused.value = !paused.value }) {
                        Text(text = if (paused.value) "Resume" else "Pause")
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Text("Day ${date.day.value} Month ${date.month.value} Year ${date.year.value}")

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
                StocksList(stocks, player)
            }
            AnimatedVisibility(selectedOption.value == "Bank") {
                BankScreen(banks, player, date)
            }
            AnimatedVisibility(selectedOption.value == "Player") {
                PlayerUI(player,banks)
            }
            AnimatedVisibility(selectedOption.value == "Mail") {
                MailUI(stocks, paused, player, news, date)
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

@Composable
fun TopBarIconMail(
    icon: Painter,
    selected: Boolean,
    hasUnreadMessage: MutableState<Boolean>,
    onSelect: () -> Unit
) {
    var isBlinking by remember { mutableStateOf(false) }
    var color by remember { mutableStateOf(Color.Gray) }

    LaunchedEffect(hasUnreadMessage.value) {
        while (true) {
            if (hasUnreadMessage.value && !selected) {
                isBlinking = !isBlinking
                color = if (isBlinking) Color.Yellow else Color.Gray
            } else {
                color = Color.Gray
            }
            delay(500) // Wait for 500 milliseconds
        }
    }
    Column(
    ) {
        IconButton(
            onClick = onSelect,
            content = {
                Icon(
                    painter = icon,
                    contentDescription = null,
                    tint = if (selected) MaterialTheme.colorScheme.primary else color,
                    modifier = Modifier
                        .size(100.dp)
                )
            }
        )
    }
}