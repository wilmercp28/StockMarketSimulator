package com.example.stockmarketsimulator.ui.game

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.stockmarketsimulator.data.Date
import com.example.stockmarketsimulator.data.News
import com.example.stockmarketsimulator.data.SaveGameDataClass
import com.example.stockmarketsimulator.data.Stock
import com.example.stockmarketsimulator.data.YearSummaryData
import com.example.stockmarketsimulator.data.getInitialStocks
import com.example.stockmarketsimulator.data.getInitialYearSummaryList
import com.example.stockmarketsimulator.funtions.Bank
import com.example.stockmarketsimulator.funtions.GameStockMarket
import com.example.stockmarketsimulator.funtions.Player
import com.example.stockmarketsimulator.funtions.getAllSaveGames
import com.example.stockmarketsimulator.funtions.getFirstEmptySaveSlot
import com.example.stockmarketsimulator.funtions.getInitialBanks
import com.example.stockmarketsimulator.funtions.getInitialPlayer
import com.google.gson.Gson
import kotlinx.coroutines.delay

@Composable
fun MainMenu(dataStore: DataStore<Preferences>) {
    var saveSlot = 0
    LaunchedEffect(Unit){
        saveSlot = getFirstEmptySaveSlot(dataStore)
    }
    val date = remember { mutableStateOf(Date()) }
    val stocks = remember { getInitialStocks() }
    val banks = remember { getInitialBanks() }
    val player = remember { getInitialPlayer() }
    val yearlySummaryList = remember { getInitialYearSummaryList() }
    val gameStart = remember { mutableStateOf(false) }
    val loadingGame = remember { mutableStateOf(false) }
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
    AnimatedVisibility(!gameStart.value && loadingGame.value) {
        LoadGame(gameStart, loadingGame, date, stocks, banks, player, yearlySummaryList, news,dataStore)
    }
    AnimatedVisibility(gameStart.value) {
        GameStockMarket(
            dataStore,
            date.value,
            stocks,
            banks,
            player,
            yearlySummaryList,
            news,
            gameStart,
            saveSlot
        )
    }
    AnimatedVisibility(!gameStart.value && !loadingGame.value) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Welcome To\nThe Stock Market Game",
                fontSize = 35.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = {
                newGame(gameStart, date, stocks, banks, player, yearlySummaryList, news,saveSlot)

            }) {
                Text(text = "Start New Game", fontSize = 20.sp)
            }
            Button(onClick = {
                loadingGame.value = true
            }) {
                Text(text = "Load Save", fontSize = 20.sp)
            }
            Button(onClick = {

            }) {
                Text(text = "Quit", fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun LoadGame(
    gameStart: MutableState<Boolean>,
    loadingGame: MutableState<Boolean>,
    date: MutableState<Date>,
    stocks: SnapshotStateList<Stock>,
    banks: SnapshotStateList<Bank>,
    player: Player,
    yearlySummaryList: SnapshotStateList<YearSummaryData>,
    news: SnapshotStateList<News>,
    dataStore: DataStore<Preferences>
) {
    var savedGames by remember { mutableStateOf<List<SaveGameDataClass>>(emptyList()) }
    LaunchedEffect(Unit) {
        savedGames = getAllSaveGames(dataStore)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                10.dp
            )
    ) {
        Row {
            IconButton(onClick = { loadingGame.value = false }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", Modifier.size(40.dp))
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "Load Game", fontSize = 40.sp)
            Spacer(modifier = Modifier.weight(1f))
        }
        LazyColumn(
            content = {
                items(savedGames) { saveGame ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {


                            }
                    ) {
                        Text(text = "Save Game ${saveGame.player.playerName}")
                    }
                }
            }
        )
    }
}


fun newGame(
    gameStart: MutableState<Boolean>,
    date: MutableState<Date>,
    stocks: MutableList<Stock>,
    banks: MutableList<Bank>,
    player: Player,
    yearlySummaryList: MutableList<YearSummaryData>,
    news: MutableList<News>,
    saveSlot: Int
) {
    date.value = Date()
    gameStart.value = true
}