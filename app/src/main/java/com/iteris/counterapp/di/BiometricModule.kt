package com.iteris.counterapp.di

import com.iteris.counterapp.data.datasources.BiometricDataSourceImpl
import com.iteris.counterapp.data.repositories.BiometricRepositoryImpl
import com.iteris.counterapp.domain.datasources.BiometricDataSource
import com.iteris.counterapp.domain.repositories.BiometricRepository
import com.iteris.counterapp.domain.usecases.biometric_auth.AuthenticateWithBiometryUseCase
import com.iteris.counterapp.domain.usecases.biometric_auth.AuthenticateWithBiometryUseCaseImpl
import com.iteris.counterapp.domain.usecases.biometric_auth.CanAuthenticateWithBiometryUseCase
import com.iteris.counterapp.domain.usecases.biometric_auth.CanAuthenticateWithBiometryUseCaseImpl
import com.iteris.counterapp.domain.usecases.biometric_auth.ListenToBiometricAuthUseCase
import com.iteris.counterapp.domain.usecases.biometric_auth.ListenToBiometricAuthUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BiometricModuleActivityScope {

    @Provides
    @Singleton
    fun provideBiometricDataSource(): BiometricDataSource {
        return BiometricDataSourceImpl()
    }

    @Provides
    @Singleton
    fun provideBiometricRepository(biometricDataSource: BiometricDataSource): BiometricRepository {
        return BiometricRepositoryImpl(biometricDataSource)
    }

    @Provides
    @Singleton
    fun provideAuthenticateWithBiometryUseCase(biometricRepository: BiometricRepository): AuthenticateWithBiometryUseCase {
        return AuthenticateWithBiometryUseCaseImpl(biometricRepository)
    }

    @Provides
    @Singleton
    fun provideCanAuthenticateWithBiometryUseCase(biometricRepository: BiometricRepository): CanAuthenticateWithBiometryUseCase {
        return CanAuthenticateWithBiometryUseCaseImpl(biometricRepository)
    }

    @Provides
    @Singleton
    fun provideListenToBiometricAuthUseCase(repository: BiometricRepository): ListenToBiometricAuthUseCase {
        return ListenToBiometricAuthUseCaseImpl(repository)
    }
}
