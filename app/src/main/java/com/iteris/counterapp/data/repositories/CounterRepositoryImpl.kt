package com.iteris.counterapp.data.repositories

import com.iteris.counterapp.core.exceptions.GenericException
import com.iteris.counterapp.data.extensions.toEntity
import com.iteris.counterapp.data.extensions.toLocalStorageModel
import com.iteris.counterapp.domain.datasources.CounterDataSource
import com.iteris.counterapp.domain.entities.CounterEntity
import com.iteris.counterapp.domain.repositories.CounterRepository
import javax.inject.Inject

class CounterRepositoryImpl @Inject constructor(private val counterDataSource: CounterDataSource) :
    CounterRepository {
    override suspend fun create(counter: CounterEntity): Result<Unit> {
        return try {
            counterDataSource.create(counter.toLocalStorageModel())
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(GenericException("Error while creating counter"))
        }
    }

    override suspend fun readAll(): Result<ArrayList<CounterEntity>> {
        return try {
            val models = counterDataSource.readAll()
            Result.success(models.map { it.toEntity() }.toCollection(ArrayList()))

        } catch (e: Exception) {
            Result.failure(GenericException("Error while loading counters"))
        }
    }

    override suspend fun update(counter: CounterEntity): Result<Unit> {
        return try {
            counterDataSource.update(counter.toLocalStorageModel())
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(GenericException("Error while updating counter"))
        }
    }

    override suspend fun delete(counter: CounterEntity): Result<Unit> {
        return try {
            counterDataSource.delete(counter.toLocalStorageModel())
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(GenericException("Error while deleting counter"))
        }
    }

    override suspend fun deleteAll(): Result<Unit> {
        return try {
            Result.success(counterDataSource.deleteAll())
        } catch (e: Exception){
            Result.failure(GenericException("Erro while deleting counters"))
        }
    }
}
