package com.iteris.counterapp.domain.datasources

import com.iteris.counterapp.data.models.CounterLocalStorageModel

interface CounterDataSource {
    fun create(data: CounterLocalStorageModel)
    fun readAll(): ArrayList<CounterLocalStorageModel>
    fun update(data: CounterLocalStorageModel)
    fun delete(data: CounterLocalStorageModel)
}
