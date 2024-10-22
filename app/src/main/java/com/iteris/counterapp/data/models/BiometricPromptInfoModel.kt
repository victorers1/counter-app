package com.iteris.counterapp.data.models

import com.iteris.counterapp.domain.entities.biometric_auth.BiometricPromptInfoEntity

data class BiometricPromptInfoModel(
    val title: String,
    val description: String,
    val negativeButtonText: String,
)

fun BiometricPromptInfoModel.toEntity() = BiometricPromptInfoEntity(
    title = this.title,
    description = this.description,
    negativeButtonText = this.negativeButtonText,
)

fun BiometricPromptInfoEntity.toModel() = BiometricPromptInfoModel(
    title = this.title,
    description = this.description,
    negativeButtonText = this.negativeButtonText,
)
