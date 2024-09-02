package com.iteris.counterapp.di

import com.iteris.counterapp.domain.repositories.AppStartupInfoRepository
import com.iteris.counterapp.domain.usecases.ReadAppStartupInfoUseCase
import com.iteris.counterapp.domain.usecases.ReadStartupInfoUseCaseImpl
import com.iteris.counterapp.domain.usecases.WriteAppStartupInfoUseCase
import com.iteris.counterapp.domain.usecases.WriteAppStartupInfoUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppStartupInfoUseCaseModule {

    @Provides
    fun provideReadAppStartupInfoUseCase(repository: AppStartupInfoRepository): ReadAppStartupInfoUseCase {
        return ReadStartupInfoUseCaseImpl(repository)
    }

    @Provides
    fun provideWriteAppStartupInfoUseCase(repository: AppStartupInfoRepository): WriteAppStartupInfoUseCase {
        return WriteAppStartupInfoUseCaseImpl(repository)
    }
}
