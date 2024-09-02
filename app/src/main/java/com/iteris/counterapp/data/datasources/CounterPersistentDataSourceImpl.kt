package com.iteris.counterapp.data.datasources

import com.iteris.counterapp.data.extensions.toLocalStorageModel
import com.iteris.counterapp.data.extensions.toRoomModel
import com.iteris.counterapp.data.models.CounterLocalStorageModel
import com.iteris.counterapp.domain.datasources.CounterDataSource

class CounterPersistentDataSourceImpl(private val dao: CounterDao) : CounterDataSource {

    override fun create(data: CounterLocalStorageModel) {
        dao.createCounter(data.toRoomModel())
    }

    override fun readAll(): ArrayList<CounterLocalStorageModel> {
        return dao.readAllCounters().map { it.toLocalStorageModel() }.toCollection(ArrayList())
    }

    override fun update(data: CounterLocalStorageModel) {
        return dao.updateCounter(data.toRoomModel())
    }

    override fun delete(data: CounterLocalStorageModel) {
        dao.deleteCounter(data.toRoomModel())
    }
}
