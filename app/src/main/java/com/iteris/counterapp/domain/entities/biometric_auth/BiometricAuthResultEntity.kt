package com.iteris.counterapp.domain.entities.biometric_auth

sealed class BiometricAuthResultEntity(val message: String) {
    /// Device doesn't have support to biometric auth
    data object NoHardware :
        BiometricAuthResultEntity("Device doesn't have support to biometric auth")

    /// Device has support to biometric, but the hardware is busy (maybe another app is using it)
    data object HardwareIsBusy :
        BiometricAuthResultEntity("Device has support to biometric, but the hardware is busy")

    /// Device has support to biometric auth, but user hasn't set any fingerprint or faceID yet
    data object UserNotEnrolled :
        BiometricAuthResultEntity("No biometry enrolled. You need to set at least one method.")

    /// Devide got an internal error while trying to authenticate. It wasn't user's fault
    data class AuthenticationError(val error: String) :
        BiometricAuthResultEntity("Authentication error: $error")

    /// Device didn't recognized the user that is trying to authenticate
    data object AuthenticationFailed : BiometricAuthResultEntity("Authentication failed")

    /// Device has successfully recognized the user
    data object AuthenticationSucceeded : BiometricAuthResultEntity("Authentication succeeded")

}
