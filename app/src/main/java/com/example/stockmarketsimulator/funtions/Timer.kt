package com.example.stockmarketsimulator.funtions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Update(
    paused: MutableState<Boolean>,
    callBack: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(paused.value) {
        coroutineScope.launch(Dispatchers.Default) {
            while (!paused.value) {
                delay(100)
                if (!paused.value) {
                    callBack()
                }
            }
        }
    }
}