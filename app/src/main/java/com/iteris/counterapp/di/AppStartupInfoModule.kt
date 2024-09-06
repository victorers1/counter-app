package com.iteris.counterapp.di

import android.content.Context
import com.iteris.counterapp.data.datasources.AppStartupInfoDataSourceImpl
import com.iteris.counterapp.data.repositories.AppStartupInfoRepositoryImpl
import com.iteris.counterapp.domain.repositories.AppStartupInfoRepository
import com.iteris.counterapp.domain.usecases.appstartinfo.DeleteAppStartupInfoUseCase
import com.iteris.counterapp.domain.usecases.appstartinfo.DeleteAppStartupInfoUseCaseImpl
import com.iteris.counterapp.domain.usecases.appstartinfo.ReadAppStartupInfoUseCase
import com.iteris.counterapp.domain.usecases.appstartinfo.ReadAppStartupInfoUseCaseImpl
import com.iteris.counterapp.domain.usecases.appstartinfo.WriteAppStartupInfoUseCase
import com.iteris.counterapp.domain.usecases.appstartinfo.WriteAppStartupInfoUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppStartupInfoModule {

    @Singleton
    @Provides
    fun provideAppStartupInfoDataSource(@ApplicationContext context: Context): AppStartupInfoDataSourceImpl {
        return AppStartupInfoDataSourceImpl(context)
    }

    @Singleton
    @Provides
    fun provideAppStoreStartupRepository(appStartupInfoDataSource: AppStartupInfoDataSourceImpl): AppStartupInfoRepository {
        return AppStartupInfoRepositoryImpl(appStartupInfoDataSource)
    }

    @Singleton
    @Provides
    fun provideReadAppStartupInfoUseCase(repository: AppStartupInfoRepository): ReadAppStartupInfoUseCase {
        return ReadAppStartupInfoUseCaseImpl(repository)
    }

    @Singleton
    @Provides
    fun provideWriteAppStartupInfoUseCase(repository: AppStartupInfoRepository): WriteAppStartupInfoUseCase {
        return WriteAppStartupInfoUseCaseImpl(repository)
    }

    @Singleton
    @Provides
    fun provideDeleteAppStartupInfoUseCase(repository: AppStartupInfoRepository): DeleteAppStartupInfoUseCase {
        return DeleteAppStartupInfoUseCaseImpl(repository)
    }
}
