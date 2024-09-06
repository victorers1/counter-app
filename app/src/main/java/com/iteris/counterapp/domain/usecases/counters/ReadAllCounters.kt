package com.iteris.counterapp.domain.usecases.counters

import com.iteris.counterapp.domain.entities.CounterEntity
import com.iteris.counterapp.domain.repositories.CounterRepository
import javax.inject.Inject

interface ReadAllCountersUseCase {
    suspend fun execute(): Result<ArrayList<CounterEntity>>
}

class ReadAllCountersUseCaseImpl @Inject constructor(
    private val counterRepository: CounterRepository
) : ReadAllCountersUseCase {
    override suspend fun execute(): Result<ArrayList<CounterEntity>> {
        return counterRepository.readAll()
    }
}
