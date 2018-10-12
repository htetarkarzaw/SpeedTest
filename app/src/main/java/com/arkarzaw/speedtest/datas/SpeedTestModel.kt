package com.arkarzaw.speedtest.datas

import android.arch.lifecycle.LiveData
import android.content.Context
import java.util.*

class SpeedTestModel private constructor(context: Context) : BaseModel(context) {

    companion object {
        private var INSTANCE: SpeedTestModel? = null
        fun getInstance(): SpeedTestModel {
            if (INSTANCE == null) {
                throw RuntimeException("HealthCareModel is being invoked before initializing.")
            }
            val i = INSTANCE
            return i!!
        }

        fun initSpeedTestModel(context: Context) {
            INSTANCE = SpeedTestModel(context)

        }
    }

    fun getAllSpeed(): LiveData<List<SpeedVO>>{
        return mTheDB.speedDao().getAllSpeed()
    }

    fun storeSpeed(speedVO: SpeedVO) :Long{
        val insertSpeed = mTheDB.speedDao().insertSpeed(speedVO)
        return insertSpeed
    }

    fun getTodaySpeed(): LiveData<List<SpeedVO>> {
        val c: Calendar = Calendar.getInstance()
        val year: Int = c.get(Calendar.YEAR)
        val month: Int = c.get(Calendar.MONTH)
        val day: Int = c.get(Calendar.DAY_OF_MONTH)
        return mTheDB.speedDao().getSpeedByDay(day,month,year)
    }

    fun getSpeedByDay(day: Int,month: Int,year: Int): LiveData<List<SpeedVO>>{
        return mTheDB.speedDao().getSpeedByDay(day,month,year)
    }

}