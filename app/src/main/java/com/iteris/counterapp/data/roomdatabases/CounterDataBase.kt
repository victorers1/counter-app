package com.iteris.counterapp.data.roomdatabases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.iteris.counterapp.data.datasources.CounterDao
import com.iteris.counterapp.data.models.CounterRoomModel

@Database(
    entities = [CounterRoomModel::class],
    version = 1
)
abstract class CounterDataBase : RoomDatabase() {

    abstract val dao: CounterDao

}
