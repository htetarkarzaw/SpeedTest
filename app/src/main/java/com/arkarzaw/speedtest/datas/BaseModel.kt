package com.arkarzaw.speedtest.datas

import android.content.Context
import com.arkarzaw.speedtest.persistance.SpeedTestDataBase

abstract class BaseModel protected constructor(context: Context){
    protected var mTheDB: SpeedTestDataBase = SpeedTestDataBase.getDatabase(context)
}