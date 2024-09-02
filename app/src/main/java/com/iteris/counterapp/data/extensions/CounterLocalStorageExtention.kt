package com.iteris.counterapp.data.extensions

import com.iteris.counterapp.data.models.CounterLocalStorageModel
import com.iteris.counterapp.domain.entities.CounterEntity

fun CounterEntity.toLocalStorageModel() = CounterLocalStorageModel(
    id = this.id,
    label = this.label,
    value = this.value,
)

fun CounterLocalStorageModel.toEntity() = CounterEntity(
    id = this.id ?: "",
    label = this.label ?: "",
    value = this.value ?: 0,
)
