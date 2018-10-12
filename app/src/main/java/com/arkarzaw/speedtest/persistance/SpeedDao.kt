package com.arkarzaw.speedtest.persistance

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.arkarzaw.speedtest.datas.SpeedVO

@Dao
interface SpeedDao {

    @Query("SElECT * FROM Speed")
    fun getAllSpeed():LiveData<List<SpeedVO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpeed(speedVO: SpeedVO) : Long

    @Query("SELECT * FROM Speed WHERE day = :day AND month = :month AND year = :year")
    fun getSpeedByDay(day: Int , month: Int , year: Int): LiveData<List<SpeedVO>>
}