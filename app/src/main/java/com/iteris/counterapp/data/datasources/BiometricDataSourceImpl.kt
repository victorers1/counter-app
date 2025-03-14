package com.iteris.counterapp.data.datasources

import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import com.iteris.counterapp.data.models.BiometricPromptInfoModel
import com.iteris.counterapp.domain.datasources.BiometricDataSource
import com.iteris.counterapp.domain.entities.biometric_auth.BiometricAuthResultEntity
import com.iteris.counterapp.domain.entities.biometric_auth.BiometricFeatStatusEntity
import kotlinx.coroutines.channels.Channel

class BiometricDataSourceImpl() : BiometricDataSource {
    val resultChannel = Channel<BiometricAuthResultEntity>()

    val authenticators = if (Build.VERSION.SDK_INT >= 30) {
        BIOMETRIC_STRONG or DEVICE_CREDENTIAL
    } else BIOMETRIC_STRONG

    override fun getAuthResultChannel(): Channel<BiometricAuthResultEntity> {
        return resultChannel
    }

    override suspend fun showPrompt(activity: AppCompatActivity, data: BiometricPromptInfoModel) {
        val promptInfo = BiometricPrompt.PromptInfo.Builder().setTitle(data.title)
            .setDescription(data.description).setAllowedAuthenticators(authenticators)

        if (Build.VERSION.SDK_INT < 30) {
            promptInfo.setNegativeButtonText(data.negativeButtonText)
        }

        val canAuth = canAuthenticate(activity)
        Log.d("biometric_auth", "canAuth: $canAuth")
        when (canAuth) {
            BiometricFeatStatusEntity.HardwareIsBusy -> {
                resultChannel.trySend(BiometricAuthResultEntity.HardwareIsBusy)
                return
            }

            BiometricFeatStatusEntity.UserNotEnrolled -> {
                resultChannel.trySend(BiometricAuthResultEntity.UserNotEnrolled)
                return
            }

            BiometricFeatStatusEntity.NoHardware -> {
                resultChannel.trySend(BiometricAuthResultEntity.NoHardware)
                return
            }

            else -> Unit
        }

        val prompt = BiometricPrompt(activity, object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                Log.d("biometric_auth", "onAuthenticationError: $errorCode $errString")
                resultChannel.trySend(BiometricAuthResultEntity.AuthenticationError(errString.toString()))
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                Log.d("biometric_auth", "onAuthenticationSucceeded: $result")
                resultChannel.trySend(BiometricAuthResultEntity.AuthenticationSucceeded)
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Log.d("biometric_auth", "onAuthenticationFailed")
                resultChannel.trySend(BiometricAuthResultEntity.AuthenticationFailed)
            }
        })

        prompt.authenticate(promptInfo.build())
    }

    override suspend fun canAuthenticate(activity: AppCompatActivity): BiometricFeatStatusEntity {
        val manager = BiometricManager.from(activity)
        return when (manager.canAuthenticate(authenticators)) {
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                BiometricFeatStatusEntity.HardwareIsBusy
            }

            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                BiometricFeatStatusEntity.UserNotEnrolled
            }

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                BiometricFeatStatusEntity.NoHardware
            }

            BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED -> {
                BiometricFeatStatusEntity.SecurityUpdateRequired
            }

            BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> {
                BiometricFeatStatusEntity.Unsupported
            }

            BiometricManager.BIOMETRIC_SUCCESS -> {
                BiometricFeatStatusEntity.CanAuthenticate
            }

            else -> BiometricFeatStatusEntity.StatusUnknown
        }
    }
}
