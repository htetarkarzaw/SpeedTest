package com.arkarzaw.speedtest.classes

import com.arkarzaw.speedtest.datas.SpeedVO

class Benchmark  {
    var speedTest = SpeedTest()
    fun getSpeedHourly() : SpeedVO {
        return speedTest.getSpeed()
    }
}