package com.arkarzaw.speedtest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var speed = SpeedTest.getSpeed()
        var format = SimpleDateFormat("dd/MM/yyyy hh:mm")

        Log.e("Speed", "---"+speed.downloadSpeed+" , "+speed.uploadSpeed+" , "+speed.ping+" , "+format.format(speed.time))
    }
}
