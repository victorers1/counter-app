package com.iteris.counterapp.di

import com.iteris.counterapp.data.datasources.CounterDao
import com.iteris.counterapp.data.datasources.CounterPersistentDataSourceImpl
import com.iteris.counterapp.data.repositories.CounterRepositoryImpl
import com.iteris.counterapp.domain.datasources.CounterDataSource
import com.iteris.counterapp.domain.repositories.CounterRepository
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
        return CounterPersistentDataSourceImpl(counterDao)
//        return CounterVolatileDataSourceImpl()
    }

    @Singleton
    @Provides
    fun provideCounterRepository(localStorageService: CounterDataSource): CounterRepository {
        return CounterRepositoryImpl(localStorageService)
    }
}
