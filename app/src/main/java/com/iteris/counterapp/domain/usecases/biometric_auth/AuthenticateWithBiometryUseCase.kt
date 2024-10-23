package com.iteris.counterapp.domain.usecases.biometric_auth

import androidx.appcompat.app.AppCompatActivity
import com.iteris.counterapp.domain.entities.biometric_auth.BiometricPromptInfoEntity
import com.iteris.counterapp.domain.repositories.BiometricRepository
import javax.inject.Inject

data class AuthenticateWithBiometryParams(
    val title: String,
    val description: String,
    val negativeButtonText: String = "Cancel",
    val activity: AppCompatActivity
)

interface AuthenticateWithBiometryUseCase {
    suspend fun execute(params: AuthenticateWithBiometryParams): Result<Unit>
}

class AuthenticateWithBiometryUseCaseImpl @Inject constructor(
    private val repository: BiometricRepository
) : AuthenticateWithBiometryUseCase {

    override suspend fun execute(params: AuthenticateWithBiometryParams): Result<Unit> {
        val promptInfo = BiometricPromptInfoEntity(
            title = params.title,
            description = params.description,
            negativeButtonText = params.negativeButtonText
        )
        return repository.showPrompt(params.activity, promptInfo)
    }
}
