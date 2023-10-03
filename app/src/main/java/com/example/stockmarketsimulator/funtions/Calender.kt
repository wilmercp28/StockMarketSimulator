package com.example.stockmarketsimulator.funtions

import androidx.compose.runtime.MutableState


fun calendar(day: MutableState<Int>, month: MutableState<Int>, year: MutableState<Int>) {
    day.value++
    if (day.value == 31) {
        day.value = 1
        month.value++
        if (month.value == 13) {
            month.value = 1
            year.value++
        }
    }
}