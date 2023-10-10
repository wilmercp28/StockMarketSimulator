package com.example.stockmarketsimulator.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotMutableState
import androidx.compose.runtime.snapshots.SnapshotStateList

data class Date(
    val day: MutableState<Int> = mutableStateOf(1),
    val month: MutableState<Int> = mutableStateOf(1),
    val year: MutableState<Int> = mutableStateOf(1),
    )
