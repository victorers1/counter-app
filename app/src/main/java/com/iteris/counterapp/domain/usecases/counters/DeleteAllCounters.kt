package com.iteris.counterapp.domain.usecases.counters

import com.iteris.counterapp.domain.repositories.CounterRepository
import javax.inject.Inject

interface DeleteAllCountersUseCase{
    suspend fun execute(): Result<Unit>
}

class DeleteAllCountersUseCaseImpl @Inject constructor(
    private  val counterRepository: CounterRepository
) : DeleteAllCountersUseCase {

    override suspend fun execute(): Result<Unit> {
        return counterRepository.deleteAll()
    }
}
