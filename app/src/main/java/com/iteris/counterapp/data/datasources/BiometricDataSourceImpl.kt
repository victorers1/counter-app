package com.iteris.counterapp.data.datasources

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import com.iteris.counterapp.data.models.BiometricPromptInfoModel
import com.iteris.counterapp.domain.datasources.BiometricDataSource
import com.iteris.counterapp.domain.entities.biometric_auth.BiometricAuthResultEntity
import com.iteris.counterapp.domain.entities.biometric_auth.BiometricFeatStatusEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class BiometricDataSourceImpl @Inject constructor(private val activity: AppCompatActivity) :
    BiometricDataSource {
    val manager = BiometricManager.from(activity)
    val authenticators = if (Build.VERSION.SDK_INT >= 30) {
        BIOMETRIC_STRONG or DEVICE_CREDENTIAL
    } else BIOMETRIC_STRONG

    override suspend fun authenticate(data: BiometricPromptInfoModel): BiometricAuthResultEntity {

        val promptInfo = BiometricPrompt.PromptInfo.Builder().setTitle(data.title)
            .setDescription(data.description).setAllowedAuthenticators(authenticators)

        if (Build.VERSION.SDK_INT < 30) {
            promptInfo.setNegativeButtonText(data.negativeButtonText)
        }

        when (canAuthenticate()) {
            BiometricFeatStatusEntity.HardwareIsBusy -> {
                return BiometricAuthResultEntity.HardwareIsBusy
            }

            BiometricFeatStatusEntity.UserNotEnrolled -> {
                return BiometricAuthResultEntity.UserNotEnrolled
            }

            BiometricFeatStatusEntity.NoHardware -> {
                return BiometricAuthResultEntity.NoHardware
            }

            else -> Unit
        }

        var authResult: BiometricAuthResultEntity =
            BiometricAuthResultEntity.AuthenticationError("Unknown error")

        val prompt = BiometricPrompt(activity, object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                authResult = BiometricAuthResultEntity.AuthenticationError(errString.toString())
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                authResult = BiometricAuthResultEntity.AuthenticationSucceeded
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                authResult = BiometricAuthResultEntity.AuthenticationFailed
            }
        })

        runBlocking(Dispatchers.IO) {
            prompt.authenticate(promptInfo.build())
        }

        return authResult
    }

    override suspend fun canAuthenticate(): BiometricFeatStatusEntity {
        when (manager.canAuthenticate(authenticators)) {
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                return BiometricFeatStatusEntity.HardwareIsBusy
            }

            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                return BiometricFeatStatusEntity.UserNotEnrolled
            }

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                return BiometricFeatStatusEntity.NoHardware
            }

            BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED -> {
                return BiometricFeatStatusEntity.SecurityUpdateRequired
            }

            BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> {
                return BiometricFeatStatusEntity.Unsupported
            }

            BiometricManager.BIOMETRIC_SUCCESS -> {
                return BiometricFeatStatusEntity.Success
            }

            else -> return BiometricFeatStatusEntity.StatusUnknow
        }
    }
}
