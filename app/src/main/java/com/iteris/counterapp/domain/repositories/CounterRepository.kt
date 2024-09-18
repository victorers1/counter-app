package com.iteris.counterapp.domain.repositories

import com.iteris.counterapp.domain.entities.counter.CounterEntity

interface CounterRepository {
    suspend fun create(counter: CounterEntity): Result<Unit>
    suspend fun readAll(): Result<ArrayList<CounterEntity>>
    suspend fun update(counter: CounterEntity): Result<Unit>
    suspend fun delete(counter: CounterEntity): Result<Unit>
    suspend fun deleteAll(): Result<Unit>
}
