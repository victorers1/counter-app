package com.iteris.counterapp.domain.usecases.counters

import com.iteris.counterapp.domain.entities.counter.CounterEntity
import com.iteris.counterapp.domain.repositories.CounterRepository
import javax.inject.Inject

data class CreateCounterParams(val id: String, val label: String, val value: Int)

interface CreateCounterUseCase {
    suspend fun execute(params: CreateCounterParams): Result<Unit>
}

class CreateCounterUseCaseImpl @Inject constructor(
    private val repository: CounterRepository
) : CreateCounterUseCase {
    override suspend fun execute(params: CreateCounterParams): Result<Unit> {
        val counterEntity = CounterEntity(params.id, params.label, params.value)
        return repository.create(counterEntity)
    }
}
