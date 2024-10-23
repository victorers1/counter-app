package com.iteris.counterapp.data.repositories

import androidx.appcompat.app.AppCompatActivity
import com.iteris.counterapp.data.models.toModel
import com.iteris.counterapp.domain.datasources.BiometricDataSource
import com.iteris.counterapp.domain.entities.biometric_auth.BiometricAuthResultEntity
import com.iteris.counterapp.domain.entities.biometric_auth.BiometricFeatStatusEntity
import com.iteris.counterapp.domain.entities.biometric_auth.BiometricPromptInfoEntity
import com.iteris.counterapp.domain.repositories.BiometricRepository
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject

class BiometricRepositoryImpl @Inject constructor(
    private val datasource: BiometricDataSource
) : BiometricRepository {

    override suspend fun canAuthenticate(activity: AppCompatActivity): Result<BiometricFeatStatusEntity> {
        return try {
            Result.success(datasource.canAuthenticate(activity))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun showPrompt(
        activity: AppCompatActivity,
        promptInfo: BiometricPromptInfoEntity
    ): Result<Unit> {
        return try {
            Result.success(datasource.showPrompt(activity, promptInfo.toModel()))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getAuthResultChannel(): Result<Channel<BiometricAuthResultEntity>> {
        return try {
            Result.success(datasource.getAuthResultChannel())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
