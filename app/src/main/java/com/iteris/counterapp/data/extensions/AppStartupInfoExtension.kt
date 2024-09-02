package com.iteris.counterapp.data.extensions

import com.iteris.counterapp.data.models.AppStartupInfoLocalStorageModel
import com.iteris.counterapp.domain.entities.AppStartupInfoEntity
import java.util.Date

fun AppStartupInfoEntity.toLocalStorageModel() = AppStartupInfoLocalStorageModel(
    count = this.count,
    timestamp = this.date.time
)

fun AppStartupInfoLocalStorageModel.toEntity() = AppStartupInfoEntity(
    count = this.count ?: 0,
    date = Date(this.timestamp ?: 0L)
)
