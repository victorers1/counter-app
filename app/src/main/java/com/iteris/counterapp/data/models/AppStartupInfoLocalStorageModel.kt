package com.iteris.counterapp.data.models

import com.iteris.counterapp.domain.entities.AppStartupInfoEntity
import java.util.Date

data class AppStartupInfoLocalStorageModel(
    val count: Int?,
    val timestamp: Long?
)


fun AppStartupInfoEntity.toLocalStorageModel() = AppStartupInfoLocalStorageModel(
    count = this.count,
    timestamp = this.date.time
)

fun AppStartupInfoLocalStorageModel.toEntity() = AppStartupInfoEntity(
    count = this.count ?: 0,
    date = Date(this.timestamp ?: 0L)
)
