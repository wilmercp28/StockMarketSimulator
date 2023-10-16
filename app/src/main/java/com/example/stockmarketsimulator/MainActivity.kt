package com.example.stockmarketsimulator

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.stockmarketsimulator.funtions.GameStockMarket
import com.example.stockmarketsimulator.ui.game.MainMenu
import com.example.stockmarketsimulator.ui.theme.StockMarketSimulatorTheme
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "SaveGame")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StockMarketSimulatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainMenu(dataStore)
                }
            }
        }
    }
}
