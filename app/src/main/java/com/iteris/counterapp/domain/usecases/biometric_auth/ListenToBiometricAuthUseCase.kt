package com.iteris.counterapp.domain.usecases.biometric_auth

import com.iteris.counterapp.domain.entities.biometric_auth.BiometricAuthResultEntity
import com.iteris.counterapp.domain.repositories.BiometricRepository
import kotlinx.coroutines.channels.Channel

interface ListenToBiometricAuthUseCase {
    fun execute(): Result<Channel<BiometricAuthResultEntity>>
}

class ListenToBiometricAuthUseCaseImpl(private val repository: BiometricRepository) :
    ListenToBiometricAuthUseCase {
    override fun execute(): Result<Channel<BiometricAuthResultEntity>> {
        return repository.getAuthResultChannel()
    }
}
