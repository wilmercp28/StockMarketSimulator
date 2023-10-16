package com.example.stockmarketsimulator.funtions

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.stockmarketsimulator.data.SaveGameDataClass
import com.example.stockmarketsimulator.dataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


fun getSaveGameDataFromDataStore(
    dataStore: DataStore<Preferences>,
    saveGameName: Int
): Flow<SaveGameDataClass?> {
    val key = stringPreferencesKey("save_game_data_$saveGameName")
    return dataStore.data.map { preferences ->
        val jsonString = preferences[key] ?: ""
        if (jsonString.isNotEmpty()) {
            SaveGameDataSerializer.fromJson(jsonString)
        } else {
            null
        }
    }
}

suspend fun saveGameDataToDataStore(
    saveGameData: SaveGameDataClass,
    dataStore: DataStore<Preferences>,
    saveGameName: Int
) {
    val jsonString = SaveGameDataSerializer.toJson(saveGameData)
    val key = stringPreferencesKey("save_game_data_$saveGameName")
    dataStore.edit { preferences ->
        preferences[key] = jsonString
    }
    Log.d("game", "Game Saved")
}
suspend fun getFirstEmptySaveSlot(dataStore: DataStore<Preferences>): Int {
    var emptySlotNumber = 0
    while (true) {
        val key = stringPreferencesKey("save_game_data_$emptySlotNumber")
        val preferences = dataStore.data.first()
        if (preferences[key].isNullOrEmpty()) {
            return emptySlotNumber
        }
        emptySlotNumber++
    }
}
fun isSaveSlotEmpty(
    dataStore: DataStore<Preferences>,
    saveGameName: Int
): Flow<Boolean> {
    val key = stringPreferencesKey("save_game_data_$saveGameName")
    return dataStore.data.map { preferences ->
        preferences[key].isNullOrEmpty()
    }
}

object SaveGameDataSerializer {
    private val gson = Gson()
    fun toJson(saveGameData: SaveGameDataClass): String {
        return gson.toJson(saveGameData)
    }
    fun fromJson(json: String): SaveGameDataClass {
        val type = object : TypeToken<SaveGameDataClass>() {}.type
        return gson.fromJson(json, type)
    }
}
suspend fun getAllSaveGames(dataStore: DataStore<Preferences>): List<SaveGameDataClass> {
    val maxSaveGames = 20
    val saveGames: MutableList<SaveGameDataClass> = mutableListOf()
    for (i in 0 until maxSaveGames) {
        val key = stringPreferencesKey("save_game_data_$i")
        val jsonString = dataStore.data.first()[key] ?: ""
        if (jsonString.isNotEmpty()) {
            val saveGameData = SaveGameDataSerializer.fromJson(jsonString)
            saveGames.add(saveGameData)
        }
    }
    return saveGames
}


