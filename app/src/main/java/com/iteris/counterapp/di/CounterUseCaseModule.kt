package com.iteris.counterapp.di

import com.iteris.counterapp.domain.repositories.CounterRepository
import com.iteris.counterapp.domain.usecases.CreateCounterUseCase
import com.iteris.counterapp.domain.usecases.CreateCounterUseCaseImpl
import com.iteris.counterapp.domain.usecases.DeleteCounterUseCase
import com.iteris.counterapp.domain.usecases.DeleteCounterUseCaseImpl
import com.iteris.counterapp.domain.usecases.ReadAllCountersUseCase
import com.iteris.counterapp.domain.usecases.ReadAllCountersUseCaseImpl
import com.iteris.counterapp.domain.usecases.UpdateCounterUseCase
import com.iteris.counterapp.domain.usecases.UpdateCounterUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class CounterUseCaseModule {

    @Provides
    fun provideCreateUseCase(counterRepository: CounterRepository): CreateCounterUseCase {
        return CreateCounterUseCaseImpl(counterRepository)
    }

    @Provides
    fun provideUpdateUseCase(counterRepository: CounterRepository): UpdateCounterUseCase {
        return UpdateCounterUseCaseImpl(counterRepository)
    }

    @Provides
    fun provideDeleteUseCase(counterRepository: CounterRepository): DeleteCounterUseCase {
        return DeleteCounterUseCaseImpl(counterRepository)
    }

    @Provides
    fun provideGetAllUseCase(counterRepository: CounterRepository): ReadAllCountersUseCase {
        return ReadAllCountersUseCaseImpl(counterRepository)
    }

}
