package com.iteris.counterapp.domain.usecases.counters

import com.iteris.counterapp.domain.entities.CounterEntity
import com.iteris.counterapp.domain.repositories.CounterRepository
import javax.inject.Inject

data class DeleteCounterParams(val id: String, val label: String, val value: Int)

interface DeleteCounterUseCase {
    suspend fun execute(params: DeleteCounterParams): Result<Unit>
}

class DeleteCounterUseCaseImpl @Inject constructor(
    private val repository: CounterRepository
) : DeleteCounterUseCase {
    override suspend fun execute(params: DeleteCounterParams): Result<Unit> {
        val entity = CounterEntity(params.id, params.label, params.value)
        return repository.delete(entity)
    }
}
