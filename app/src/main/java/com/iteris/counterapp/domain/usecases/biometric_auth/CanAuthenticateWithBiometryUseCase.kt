package com.iteris.counterapp.domain.usecases.biometric_auth

import com.iteris.counterapp.domain.entities.biometric_auth.BiometricFeatStatusEntity
import com.iteris.counterapp.domain.repositories.BiometricRepository
import javax.inject.Inject

interface CanAuthenticateWithBiometryUseCase {
    suspend fun execute(): Result<BiometricFeatStatusEntity>
}

class CanAuthenticateWithBiometryUseCaseImpl @Inject constructor(
    private val repository: BiometricRepository
) : CanAuthenticateWithBiometryUseCase {

    override suspend fun execute(): Result<BiometricFeatStatusEntity> {
        return repository.canAuthenticate()
    }
}
