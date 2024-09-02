package com.iteris.counterapp.di

import android.content.Context
import androidx.room.Room
import com.iteris.counterapp.data.datasources.CounterDao
import com.iteris.counterapp.data.roomdatabases.CounterDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideCounterDatabase(
        @ApplicationContext context: Context
    ): CounterDataBase {
        return Room.databaseBuilder(
            context,
            CounterDataBase::class.java,
            "counter"
        ).build()
    }

    @Provides
    @Singleton
    fun providesDao(dataBase: CounterDataBase): CounterDao {
        return dataBase.dao
    }
}
