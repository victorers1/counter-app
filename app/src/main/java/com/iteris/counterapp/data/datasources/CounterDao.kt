package com.iteris.counterapp.data.datasources

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.iteris.counterapp.data.models.CounterRoomModel

@Dao
interface CounterDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun createCounter(counter: CounterRoomModel)

    @Query("SELECT * FROM counter")
    fun readAllCounters(): List<CounterRoomModel>

    @Update
    fun updateCounter(counter: CounterRoomModel)

    @Delete
    fun deleteCounter(counter: CounterRoomModel)

}
