package com.iteris.counterapp.domain.usecases.biometric_auth

import androidx.appcompat.app.AppCompatActivity
import com.iteris.counterapp.domain.entities.biometric_auth.BiometricFeatStatusEntity
import com.iteris.counterapp.domain.repositories.BiometricRepository
import javax.inject.Inject

data class CanAuthenticateWithBiometryParams(
    val activity: AppCompatActivity
)

interface CanAuthenticateWithBiometryUseCase {
    suspend fun execute(params: CanAuthenticateWithBiometryParams): Result<BiometricFeatStatusEntity>
}

class CanAuthenticateWithBiometryUseCaseImpl @Inject constructor(
    private val repository: BiometricRepository
) : CanAuthenticateWithBiometryUseCase {

    override suspend fun execute(params: CanAuthenticateWithBiometryParams): Result<BiometricFeatStatusEntity> {
        return repository.canAuthenticate(params.activity)
    }
}
