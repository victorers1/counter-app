package com.iteris.counterapp.domain.usecases.counters

import com.iteris.counterapp.domain.entities.counter.CounterEntity
import com.iteris.counterapp.domain.repositories.CounterRepository
import javax.inject.Inject

data class UpdateCounterParams(val id: String, val newLabel: String, val newValue: Int)

interface UpdateCounterUseCase {
    suspend fun execute(params: UpdateCounterParams): Result<Unit>
}

class UpdateCounterUseCaseImpl @Inject constructor(
    private val repository: CounterRepository
) : UpdateCounterUseCase {
    override suspend fun execute(params: UpdateCounterParams): Result<Unit> {
        val counterEntity = CounterEntity(params.id, params.newLabel, params.newValue)
        return repository.update(counterEntity)
    }
}
