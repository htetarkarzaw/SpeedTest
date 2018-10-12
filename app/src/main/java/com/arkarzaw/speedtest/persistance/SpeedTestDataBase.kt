package com.arkarzaw.speedtest.persistance

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.arkarzaw.speedtest.datas.SpeedVO


@Database(entities = [(SpeedVO::class)], version = 1 , exportSchema = false)
abstract class SpeedTestDataBase : RoomDatabase() {

    abstract fun speedDao() : SpeedDao
    companion object {
        private val DB_NAME = "SpeedTest_DB"
        private var INSTANCE : SpeedTestDataBase? = null

        fun getDatabase(context: Context) : SpeedTestDataBase {
            if(INSTANCE ==null){
                INSTANCE = Room.databaseBuilder(context, SpeedTestDataBase::class.java, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
            }
            val i = INSTANCE
            return i!!
        }
    }
}