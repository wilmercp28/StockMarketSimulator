package com.example.stockmarketsimulator.funtions

import com.example.stockmarketsimulator.data.Date


fun calendar(date: Date, saveGame:() -> Unit) {
    date.day.value++
    if (date.day.value == 31) {
        date.day.value = 1
        date.month.value++
        if (date.month.value == 13) {
            date.month.value = 1
            date.year.value++
            saveGame()
        }
    }
}