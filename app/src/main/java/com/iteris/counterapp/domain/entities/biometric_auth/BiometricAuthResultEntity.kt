package com.iteris.counterapp.domain.entities.biometric_auth

sealed interface BiometricAuthResultEntity {
    /// Device doesn't have support to biometric auth
    data object NoHardware : BiometricAuthResultEntity

    /// Device has support to biometric, but the hardware is busy (maybe another app is using it)
    data object HardwareIsBusy : BiometricAuthResultEntity

    /// Device has support to biometric auth, but user hasn't set any fingerprint or faceID yet
    data object UserNotEnrolled : BiometricAuthResultEntity

    /// Devide got an internal error while trying to authenticate. It wasn't user's fault
    data class AuthenticationError(val error: String) : BiometricAuthResultEntity

    /// Device didn't recognized the user that is trying to authenticate
    data object AuthenticationFailed : BiometricAuthResultEntity

    /// Device has successfully recognized the user
    data object AuthenticationSucceeded : BiometricAuthResultEntity

}
