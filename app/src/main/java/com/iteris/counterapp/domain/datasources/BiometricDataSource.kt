package com.iteris.counterapp.domain.datasources

import com.iteris.counterapp.data.models.BiometricPromptInfoModel
import com.iteris.counterapp.domain.entities.biometric_auth.BiometricAuthResultEntity
import com.iteris.counterapp.domain.entities.biometric_auth.BiometricFeatStatusEntity

interface BiometricDataSource {
    /// Returns Biometric Sensors status (Hardware is busy, No hardware, Not enrolled, etc)
    suspend fun canAuthenticate(): BiometricFeatStatusEntity
    suspend fun authenticate(data: BiometricPromptInfoModel): BiometricAuthResultEntity
}
