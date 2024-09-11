package com.iteris.counterapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "counter")
data class CounterRoomModel(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val label: String,
    val value: Int
)


fun CounterLocalStorageModel.toRoomModel() = CounterRoomModel(
    id = this.id ?: "",
    label = this.label ?: "",
    value = this.value ?: 0
)

fun CounterRoomModel.toLocalStorageModel() = CounterLocalStorageModel(
    id = this.id,
    label = this.label,
    value = this.value
)
