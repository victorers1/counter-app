package com.iteris.counterapp.data.datasources

import com.iteris.counterapp.data.models.CounterLocalStorageModel
import com.iteris.counterapp.domain.datasources.CounterDataSource


class CounterVolatileDataSourceImpl : CounterDataSource {
    private var counters = arrayListOf(
        CounterLocalStorageModel("0", "Drink Water", 0),
        CounterLocalStorageModel("1", "Take a walk", -10),
        CounterLocalStorageModel("2", "Blink eyes", 20),
    )

    override fun create(data: CounterLocalStorageModel) {
        counters.add(0, data.copy(id = generateId()))
    }

    override fun readAll(): ArrayList<CounterLocalStorageModel> {
        return counters.map { it.copy() }.toCollection(ArrayList())
    }

    override fun update(data: CounterLocalStorageModel) {
        counters.replaceAll { if (it.id == data.id) data else it }
    }

    override fun delete(data: CounterLocalStorageModel) {
        counters.removeIf { it.id == data.id }
    }

    private fun generateId(): String {
        return java.util.UUID.randomUUID().toString()
    }
}
