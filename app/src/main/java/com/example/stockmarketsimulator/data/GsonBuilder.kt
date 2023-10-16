package com.example.stockmarketsimulator.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

class MutableStateTypeAdapter<T>(private val delegate: TypeAdapter<T>) : TypeAdapter<MutableState<T>>() {
    override fun write(out: JsonWriter, value: MutableState<T>) {
        delegate.write(out, value.value)
    }

    override fun read(`in`: JsonReader): MutableState<T> {
        val value = delegate.read(`in`)
        return mutableStateOf(value)
    }
}