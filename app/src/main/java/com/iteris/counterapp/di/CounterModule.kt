package com.iteris.counterapp.di

import com.iteris.counterapp.data.datasources.CounterDao
import com.iteris.counterapp.data.datasources.CounterPersistentDataSourceImpl
import com.iteris.counterapp.data.repositories.CounterRepositoryImpl
import com.iteris.counterapp.domain.datasources.CounterDataSource
import com.iteris.counterapp.domain.repositories.CounterRepository
import com.iteris.counterapp.domain.usecases.counters.CreateCounterUseCase
import com.iteris.counterapp.domain.usecases.counters.CreateCounterUseCaseImpl
import com.iteris.counterapp.domain.usecases.counters.DeleteAllCountersUseCase
import com.iteris.counterapp.domain.usecases.counters.DeleteAllCountersUseCaseImpl
import com.iteris.counterapp.domain.usecases.counters.DeleteCounterUseCase
import com.iteris.counterapp.domain.usecases.counters.DeleteCounterUseCaseImpl
import com.iteris.counterapp.domain.usecases.counters.ReadAllCountersUseCase
import com.iteris.counterapp.domain.usecases.counters.ReadAllCountersUseCaseImpl
import com.iteris.counterapp.domain.usecases.counters.UpdateCounterUseCase
import com.iteris.counterapp.domain.usecases.counters.UpdateCounterUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CounterModule {

    @Singleton
    @Provides
    fun provideCounterDataSource(counterDao: CounterDao): CounterDataSource {
        return CounterPersistentDataSourceImpl(counterDao) // return CounterVolatileDataSourceImpl()
    }

    @Singleton
    @Provides
    fun provideCounterRepository(localStorageService: CounterDataSource): CounterRepository {
        return CounterRepositoryImpl(localStorageService)
    }

    @Singleton
    @Provides
    fun provideCreateUseCase(repository: CounterRepository): CreateCounterUseCase {
        return CreateCounterUseCaseImpl(repository)
    }

    @Singleton
    @Provides
    fun provideUpdateUseCase(repository: CounterRepository): UpdateCounterUseCase {
        return UpdateCounterUseCaseImpl(repository)
    }

    @Singleton
    @Provides
    fun provideDeleteUseCase(repository: CounterRepository): DeleteCounterUseCase {
        return DeleteCounterUseCaseImpl(repository)
    }

    @Singleton
    @Provides
    fun provideGetAllUseCase(repository: CounterRepository): ReadAllCountersUseCase {
        return ReadAllCountersUseCaseImpl(repository)
    }

    @Singleton
    @Provides
    fun provideDeleteAllCountersUseCase(repository: CounterRepository): DeleteAllCountersUseCase {
        return DeleteAllCountersUseCaseImpl(repository)
    }
}
