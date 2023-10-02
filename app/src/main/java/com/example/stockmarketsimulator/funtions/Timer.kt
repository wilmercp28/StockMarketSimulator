package com.example.stockmarketsimulator.funtions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import kotlinx.coroutines.delay

@Composable
fun Update(
    delayTime: Long,
    paused: MutableState<Boolean>,
    callBack:() -> Unit
){
    LaunchedEffect(Unit){
        while (!paused.value){
            delay(delayTime)
            callBack()
        }
    }
}