package com.iteris.counterapp.data.repositories

import com.iteris.counterapp.data.models.toModel
import com.iteris.counterapp.domain.datasources.BiometricDataSource
import com.iteris.counterapp.domain.entities.biometric_auth.BiometricAuthResultEntity
import com.iteris.counterapp.domain.entities.biometric_auth.BiometricFeatStatusEntity
import com.iteris.counterapp.domain.entities.biometric_auth.BiometricPromptInfoEntity
import com.iteris.counterapp.domain.repositories.BiometricRepository
import javax.inject.Inject

class BiometricRepositoryImpl @Inject constructor(
    private val datasource: BiometricDataSource
) : BiometricRepository {

    override suspend fun canAuthenticate(): Result<BiometricFeatStatusEntity> {
        return try {
            Result.success(datasource.canAuthenticate())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun authenticate(params: BiometricPromptInfoEntity): Result<BiometricAuthResultEntity> {
        return try {
            Result.success(datasource.authenticate(params.toModel()))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
