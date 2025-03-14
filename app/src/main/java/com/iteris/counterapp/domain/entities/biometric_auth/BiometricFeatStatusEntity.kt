package com.iteris.counterapp.domain.entities.biometric_auth

sealed interface BiometricFeatStatusEntity {
    /**
     * Equivalent to {@link android.hardware.biometrics.BiometricManager#BIOMETRIC_SUCCESS}
     */
    data object CanAuthenticate : BiometricFeatStatusEntity

    /**
     * Equivalent to {@link android.hardware.biometrics.BiometricManager#BIOMETRIC_STATUS_UNKNOWN}
     */
    data object StatusUnknown : BiometricFeatStatusEntity

    /**
     * Equivalent to {@link android.hardware.biometrics.BiometricManager#BIOMETRIC_ERROR_UNSUPPORTED}
     */
    data object Unsupported : BiometricFeatStatusEntity

    /**
     * Equivalent to {@link android.hardware.biometrics.BiometricManager#BIOMETRIC_ERROR_HW_UNAVAILABLE}
     */
    data object HardwareIsBusy : BiometricFeatStatusEntity

    /**
     * Equivalent to {@link android.hardware.biometrics.BiometricManager#BIOMETRIC_ERROR_NONE_ENROLLED}
     */
    data object UserNotEnrolled : BiometricFeatStatusEntity

    /**
     * Equivalent to {@link android.hardware.biometrics.BiometricManager#BIOMETRIC_ERROR_NO_HARDWARE}
     */
    data object NoHardware : BiometricFeatStatusEntity

    /**
     * Equivalent to {@link android.hardware.biometrics.BiometricManager#BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED}
     */
    data object SecurityUpdateRequired : BiometricFeatStatusEntity
}
