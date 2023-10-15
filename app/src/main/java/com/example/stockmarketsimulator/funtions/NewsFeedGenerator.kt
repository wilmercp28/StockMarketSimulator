package com.example.stockmarketsimulator.funtions

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.stockmarketsimulator.data.Date
import com.example.stockmarketsimulator.data.News
import com.example.stockmarketsimulator.data.Stock
import kotlin.random.Random

fun newsFeedGenerator(
    stocks: SnapshotStateList<Stock>,
    news: SnapshotStateList<News>,
    date: Date,
    paused: MutableState<Boolean>
) {
    var numberOfEvents = 0
    var alreadyGeneratedNews = false
    val randomDay = Random.nextInt(30) + 1
    if (date.day.value == 1 && date.month.value % 4 == 0) {
        for (stock in stocks) {
            if (stock.inEvent && stock.eventEnd.value == date) {
                stock.inEvent = false
            }
            val random = Math.random()
            if (!alreadyGeneratedNews) {
                if (random <= 0.9 && date.day.value == randomDay) {
                    if (!stock.inEvent) {
                        stock.inEvent = true
                        stock.eventEnd = mutableStateOf(
                            Date(
                                date.day,
                                date.month,
                                mutableStateOf(date.year.value + 1)
                            )
                        )
                        news += generateRandomNews(stock, date) {
                            stock.demand += it
                        }
                        numberOfEvents += 1
                        alreadyGeneratedNews = true
                        paused.value = true
                    }
                }
            }
        }
    }
}

fun generateRandomNews(stock: Stock, date: Date, onEvent: (Int) -> Unit): News {
    val newsTitles = arrayOf(
        "Major Partnership Announced for ${stock.name}",
        "${stock.name} Reports Strong Earnings Growth",
        "Investors Bullish on ${stock.name} Stock",
        "New Product Launch Drives ${stock.name} Shares Up",
        "${stock.name} Faces Legal Challenges",
        "Analysts Downgrade ${stock.name} Stock Rating",
        "Market Volatility Impacts ${stock.name} Shares",
        "Supply Chain Disruptions Affect ${stock.name} Production"
    )
    val newsMessages = arrayOf(
        "${stock.name} has formed a strategic partnership with a leading tech company, expanding its market presence.",
        "${stock.name} released its quarterly earnings report, showing substantial growth in revenue and profits.",
        "Investors show confidence in ${stock.name} as the company continues to perform well in the market.",
        "The launch of a highly anticipated product boosts investor enthusiasm, driving ${stock.name} shares up.",
        "${stock.name} is facing legal challenges related to a recent business decision, causing concerns among investors.",
        "Financial analysts have downgraded the rating of ${stock.name} stock, citing concerns about future performance.",
        "Market volatility and economic uncertainties are impacting ${stock.name} shares, leading to fluctuations in stock prices.",
        "Supply chain disruptions are affecting the production of ${stock.name}, leading to potential delays and reduced output."
    )
    val changeOnDemand = arrayOf(
        50,
        50,
        50,
        50,
        -50,
        -50,
        -50,
        -50
    )
    val randomIndex = (newsTitles.indices).random()
    onEvent(changeOnDemand[randomIndex])
    return News(
        title = newsTitles[randomIndex],
        stockName = stock.name,
        date = "Day ${date.day.value} Month ${date.month.value} Year ${date.year.value}",
        message = newsMessages[randomIndex],
    )
}




