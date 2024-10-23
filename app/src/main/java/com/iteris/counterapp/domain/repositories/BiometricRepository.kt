package com.iteris.counterapp.domain.repositories

import androidx.appcompat.app.AppCompatActivity
import com.iteris.counterapp.domain.entities.biometric_auth.BiometricAuthResultEntity
import com.iteris.counterapp.domain.entities.biometric_auth.BiometricFeatStatusEntity
import com.iteris.counterapp.domain.entities.biometric_auth.BiometricPromptInfoEntity
import kotlinx.coroutines.channels.Channel

interface BiometricRepository {
    suspend fun canAuthenticate(activity: AppCompatActivity): Result<BiometricFeatStatusEntity>
    suspend fun showPrompt(
        activity: AppCompatActivity,
        promptInfo: BiometricPromptInfoEntity
    ): Result<Unit>

    fun getAuthResultChannel(): Result<Channel<BiometricAuthResultEntity>>
}
