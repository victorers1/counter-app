package com.iteris.counterapp.domain.datasources

import androidx.appcompat.app.AppCompatActivity
import com.iteris.counterapp.data.models.BiometricPromptInfoModel
import com.iteris.counterapp.domain.entities.biometric_auth.BiometricAuthResultEntity
import com.iteris.counterapp.domain.entities.biometric_auth.BiometricFeatStatusEntity
import kotlinx.coroutines.channels.Channel

interface BiometricDataSource {
    /// Returns Biometric Sensors status (Hardware is busy, No hardware, Not enrolled, etc)
    suspend fun canAuthenticate(activity: AppCompatActivity): BiometricFeatStatusEntity

    /// Display the biometric auth prompt
    suspend fun showPrompt(activity: AppCompatActivity, data: BiometricPromptInfoModel)

    /// Retrieve the Channel with the auth result
    fun getAuthResultChannel(): Channel<BiometricAuthResultEntity>
}
