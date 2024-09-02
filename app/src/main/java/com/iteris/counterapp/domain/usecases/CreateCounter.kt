package com.iteris.counterapp.domain.usecases

import com.iteris.counterapp.domain.entities.CounterEntity
import com.iteris.counterapp.domain.repositories.CounterRepository
import javax.inject.Inject

data class CreateCounterParams(val id: String, val label: String, val value: Int)

interface CreateCounterUseCase {
    suspend fun execute(params: CreateCounterParams): Result<Unit>
}

class CreateCounterUseCaseImpl @Inject constructor(
    private val counterRepository: CounterRepository
) : CreateCounterUseCase {
    override suspend fun execute(params: CreateCounterParams): Result<Unit> {
        val counterEntity = CounterEntity(params.id, params.label, params.value)
        return counterRepository.create(counterEntity)
    }
}
