package com.iteris.counterapp.domain.datasources

import com.iteris.counterapp.data.models.AppStartupInfoLocalStorageModel

interface AppStartupInfoDataSource {
   suspend fun read(): AppStartupInfoLocalStorageModel
   suspend fun write(data: AppStartupInfoLocalStorageModel)
   suspend fun deleteAll()
}
