package com.example.stockmarketsimulator.data

data class Stock(
    val name: String,
    val abbreviateName: String,
    val price: Long,
    val volatility: Long,
    val avgReturn: Double = 0.0
    )

fun getInitialStocks(

):MutableList<Stock>{
    return mutableListOf(
        Stock("Apple Inc.", "AAPL", 150, 2),
        Stock("Google LLC", "GOOGL", 2800, 3),
        Stock("Microsoft Corporation", "MSFT", 300, 1),
        Stock("Amazon.com, Inc.", "AMZN", 3400, 4),
        Stock("Tesla, Inc.", "TSLA", 750, 5),
        Stock("Facebook, Inc.", "FB", 350, 2),
        Stock("Netflix, Inc.", "NFLX", 550, 3),
        Stock("NVIDIA Corporation", "NVDA", 225, 4),
        Stock("PayPal Holdings, Inc.", "PYPL", 250, 2),
        Stock("Walt Disney Company", "DIS", 170, 3),
        Stock("Alphabet Inc.", "GOOG", 2700, 3),
        Stock("Visa Inc.", "V", 230, 2),
        Stock("Mastercard Incorporated", "MA", 350, 2),
        Stock("JPMorgan Chase & Co.", "JPM", 160, 4),
        Stock("Goldman Sachs Group, Inc.", "GS", 380, 5),
        Stock("Facebook Meta Platforms, Inc.", "META", 330, 3),
        Stock("Twitter, Inc.", "TWTR", 50, 4),
        Stock("Snap Inc.", "SNAP", 75, 5),
        Stock("Shopify Inc.", "SHOP", 1500, 3),
        Stock("Salesforce.com, Inc.", "CRM", 260, 2),
        Stock("Adobe Inc.", "ADBE", 650, 2)
    )
}