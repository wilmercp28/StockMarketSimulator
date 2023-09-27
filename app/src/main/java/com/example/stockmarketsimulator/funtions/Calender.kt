package com.example.stockmarketsimulator.funtions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun Calendar(

){
    val day = remember { mutableStateOf(1) }
    val month = remember{ mutableStateOf(1) }
}