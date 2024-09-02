package com.iteris.counterapp.data.extensions

import com.iteris.counterapp.data.models.CounterLocalStorageModel
import com.iteris.counterapp.data.models.CounterRoomModel

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
