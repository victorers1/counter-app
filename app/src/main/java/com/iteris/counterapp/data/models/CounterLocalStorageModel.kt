package com.iteris.counterapp.data.models

import com.iteris.counterapp.domain.entities.CounterEntity

data class CounterLocalStorageModel(
    val id: String?,
    val label: String?,
    val value: Int?,
)

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
