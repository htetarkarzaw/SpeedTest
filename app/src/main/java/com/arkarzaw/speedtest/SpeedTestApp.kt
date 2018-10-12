package com.arkarzaw.speedtest

import android.app.Application
import android.content.Intent
import com.arkarzaw.speedtest.datas.SpeedTestModel
import android.content.Context.ACTIVITY_SERVICE
import android.app.ActivityManager
import android.content.Context


class SpeedTestApp: Application() {
    companion object {
        const val TAG ="SpeedTestApp"
    }
    override fun onCreate() {
        super.onCreate()
        SpeedTestModel.initSpeedTestModel(applicationContext)
        if(startService(Intent(this, TestService::class.java))==null){
            startService(Intent(this,TestService::class.java))
        }
    }

}