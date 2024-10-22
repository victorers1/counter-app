package com.iteris.counterapp.domain.repositories

import com.iteris.counterapp.domain.entities.biometric_auth.BiometricAuthResultEntity
import com.iteris.counterapp.domain.entities.biometric_auth.BiometricFeatStatusEntity
import com.iteris.counterapp.domain.entities.biometric_auth.BiometricPromptInfoEntity

interface BiometricRepository {
    suspend fun canAuthenticate(): Result<BiometricFeatStatusEntity>
    suspend fun authenticate(params: BiometricPromptInfoEntity): Result<BiometricAuthResultEntity>
}
