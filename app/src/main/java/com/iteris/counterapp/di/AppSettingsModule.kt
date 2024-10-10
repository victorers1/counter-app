package com.iteris.counterapp.di

import android.content.Context
import com.iteris.counterapp.data.datasources.AppSettingsDataSourceImpl
import com.iteris.counterapp.data.repositories.AppSettingsRepositoryImpl
import com.iteris.counterapp.domain.datasources.AppSettingsDataSource
import com.iteris.counterapp.domain.repositories.AppSettingsRepository
import com.iteris.counterapp.domain.usecases.settings.ReadAppSettingsUseCase
import com.iteris.counterapp.domain.usecases.settings.ReadAppSettingsUseCaseImpl
import com.iteris.counterapp.domain.usecases.settings.WriteAppSettingsUseCase
import com.iteris.counterapp.domain.usecases.settings.WriteAppSettingsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppSettingsModule {

    @Provides
    @Singleton
    fun provideAppSettingsDataSource(@ApplicationContext context: Context): AppSettingsDataSource {
        return AppSettingsDataSourceImpl(context)
    }

    @Provides
    @Singleton
    fun provideAppSettingsRepository(dataSource: AppSettingsDataSource): AppSettingsRepository {
        return AppSettingsRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun providesReadSettingsUseCase(repository: AppSettingsRepository): ReadAppSettingsUseCase {
        return ReadAppSettingsUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideWriteSettingsUseCase(repository: AppSettingsRepository): WriteAppSettingsUseCase {
        return WriteAppSettingsUseCaseImpl(repository)
    }
}
