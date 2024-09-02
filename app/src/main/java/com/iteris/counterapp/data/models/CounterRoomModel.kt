package com.iteris.counterapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "counter")
data class CounterRoomModel(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val label: String,
    val value: Int
)
